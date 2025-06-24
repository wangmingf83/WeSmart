package cn.iocoder.yudao.module.smart.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.fileservice.service.IFileService;
import cn.iocoder.yudao.module.smart.service.IWeCommunityKeywordToGroupService;
import cn.iocoder.yudao.module.smart.service.IWeKeyWordGroupSubService;
import cn.iocoder.yudao.module.smart.service.IWeKeywordGroupViewCountService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.utils.QREncode;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKeyWordGroupSub;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKeywordGroupViewCount;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeKeywordGroupTask;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.vo.WeKeywordGroupViewCountVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeKeywordGroupTaskMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@Service
public class WeCommunityKeywordToGroupServiceImpl  extends ServiceImpl<WeKeywordGroupTaskMapper, WeKeywordGroupTask> implements IWeCommunityKeywordToGroupService {



    @Autowired
    private IWeKeywordGroupViewCountService iWeKeywordGroupViewCountService;

    @Autowired
    private IWeKeyWordGroupSubService iWeKeyWordGroupSubService;

    @Autowired
    private GuituAiConfig GuituAiConfig;


    @Resource
    private IFileService fileService;

    @Override
    public List<WeKeywordGroupTask> findLists(WeKeywordGroupTask task) {

        List<WeKeywordGroupTask> groupTasks = this.list(new LambdaQueryWrapper<WeKeywordGroupTask>()
                .like(StringUtils.isNotEmpty(task.getTitle()), WeKeywordGroupTask::getTitle, task.getTitle())
                .orderByDesc(WeKeywordGroupTask::getUpdateTime));


        if(CollectionUtil.isNotEmpty(groupTasks)){
            groupTasks.stream().forEach(k->{

                WeKeywordGroupViewCountVo groupViewCountVo = iWeKeywordGroupViewCountService.countTab(k.getId());

                   if(null != groupViewCountVo){
                       k.setTotalJoinGroupNmber(groupViewCountVo.getTotalJoinGroupNmber());
                       k.setTotalViewNumber(groupViewCountVo.getTotalViewNumber());
                   }
                   long kwgn = iWeKeyWordGroupSubService.count(new LambdaQueryWrapper<WeKeyWordGroupSub>()
                                   .eq(WeKeyWordGroupSub::getKeywordGroupId,k.getId()));
                //设置关键词数量
                k.setKeyWordGroupNumber((int)kwgn);


            });
        }


        return groupTasks;
    }

    @Override
    public WeKeywordGroupTask findBaseInfo(Long id,String unionId, Boolean isCount) {
        if(isCount){
            List<WeKeywordGroupViewCount> groupViewCounts = iWeKeywordGroupViewCountService.list(new LambdaQueryWrapper<WeKeywordGroupViewCount>()
                    .eq(WeKeywordGroupViewCount::getKeywordGroupId, id)
                    .eq(WeKeywordGroupViewCount::getUnionId,unionId)
                    .apply("date_format (create_time,'%Y-%m-%d') = date_format ({0},'%Y-%m-%d')", new Date())
            );
            if(CollectionUtil.isNotEmpty(groupViewCounts)){
                WeKeywordGroupViewCount weKeywordGroupViewCount = groupViewCounts.stream().findFirst().get();
                weKeywordGroupViewCount.setViewNum(weKeywordGroupViewCount.getViewNum()+1);
                iWeKeywordGroupViewCountService.updateById(weKeywordGroupViewCount);
            }else{
                iWeKeywordGroupViewCountService.save(WeKeywordGroupViewCount.builder()
                                .keywordGroupId(id)
                                .delFlag(Constants.COMMON_STATE)
                                .viewNum(1)
                                .unionId(unionId)
                        .build());
            }
        }

        return this.getById(id);
    }

    @Override
    public void createOrUpdate(WeKeywordGroupTask groupTask) throws IOException {

        WeKeywordGroupTask weKeywordGroupTask = getById(groupTask.getId());
        if(null != weKeywordGroupTask){
            groupTask.setKeywordGroupUrl(weKeywordGroupTask.getKeywordGroupUrl());
            groupTask.setKeywordGroupQrUrl(weKeywordGroupTask.getKeywordGroupQrUrl());
        }
          if(StringUtils.isEmpty(groupTask.getKeywordGroupUrl())||StringUtils.isEmpty(groupTask.getKeywordGroupQrUrl())){
              //构造关键词群专属二维码
              String contentUrl = MessageFormat.format(GuituAiConfig.getKeyWordGroupUrl(), groupTask.getId().toString());
              String url = fileService.uploadFile(QREncode.getQRCodeMultipartFile(contentUrl, null));
              if (null != url) {
                  groupTask.setKeywordGroupUrl(contentUrl);
                  groupTask.setKeywordGroupQrUrl(url);
              }
          }
        saveOrUpdate(groupTask);
    }



}
