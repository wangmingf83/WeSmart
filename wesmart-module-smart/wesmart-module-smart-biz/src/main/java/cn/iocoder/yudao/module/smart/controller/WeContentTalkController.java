package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.service.IWeCategoryService;
import cn.iocoder.yudao.module.smart.service.IWeContentSendRecordService;
import cn.iocoder.yudao.module.smart.service.IWeContentTalkService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.dto.WeContentSendViewDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.ContentDetailQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.WeMaterialAnalyseVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.talk.WeContentTalkVo;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/content/talk")
public class WeContentTalkController extends BaseController {

    @Resource
    private IWeContentTalkService weContentTalkService;

    @Resource
    private IWeMaterialService iWeMaterialService;

    @Resource
    private IWeContentSendRecordService weContentSendRecordService;

    @Resource
    private IWeCategoryService weCategoryService;





    @PostMapping
    @Operation(summary = "保存话术")
    public AjaxResult saveContentTalk(@RequestBody WeContentTalk weContentTalk) {
        weContentTalkService.saveContentTalk(weContentTalk);
        return AjaxResult.success("保存成功");
    }

    @GetMapping("/selectPage")
    @Operation(summary = "分页查询")
    public TableDataInfo selectContentTalkPage(WeContentTalk weContentTalk) {

        //查询查看和发送次数
        //2企业话术 3客服话术
        Integer resourceType = weContentTalk.getTalkType() == 0 ? 2 : 3;
        List<WeContentSendViewDto> weContentSendViewDtoList = weContentSendRecordService.getSendViewDataTotal(resourceType);

        //查询话术列表
        startPage();
        List<WeContentTalkVo> weContentTalkVoList = weContentTalkService.selectWeContentVoList(weContentTalk);

        for (WeContentTalkVo weContentTalkVo : weContentTalkVoList) {
            String materialIds = weContentTalkVo.getMaterialIds();
            if (ObjectUtil.isNotEmpty(materialIds)) {
                List<String> list = Arrays.asList(materialIds.split(","));
                weContentTalkVo.setMaterialNum(list.size());
                for (WeContentSendViewDto weContentSendViewDto : weContentSendViewDtoList) {
//                    Long contentId = weContentSendViewDto.getContentId();
                    if (weContentTalkVo.getId().equals(weContentSendViewDto.getTalkId())) {
                    //if (list.contains(contentId.toString()) && weContentTalkVo.getId().equals(weContentSendViewDto.getTalkId())) {

                        Integer sendTotalNum = weContentTalkVo.getSendTotalNum();
                        Integer viewTotalNum = weContentTalkVo.getViewTotalNum();
                        Integer viewByTotalNum = weContentTalkVo.getViewByTotalNum();

                        weContentTalkVo.setSendTotalNum((int) (sendTotalNum + weContentSendViewDto.getSendTotalNum()));
                        weContentTalkVo.setViewTotalNum((int) (viewTotalNum + weContentSendViewDto.getViewTotalNum()));
                        weContentTalkVo.setViewByTotalNum((int) (viewByTotalNum + weContentSendViewDto.getViewByTotalNum()));
                    }
                }
            }
        }
        return getDataTable(weContentTalkVoList);
    }

    @Operation(summary = "批量删除")
    @DeleteMapping("/del/{talkIds}")
    public AjaxResult del(@PathVariable List<Long> talkIds) {
        weContentTalkService.del(talkIds);
        return AjaxResult.success();
    }

    @GetMapping("/detail/{talkId}")
    @Operation(summary = "话术详情")
    public AjaxResult getDetailByTalkId(@PathVariable Long talkId) {
        WeContentTalk weContentTalk = weContentTalkService.getOne(new LambdaQueryWrapper<WeContentTalk>().eq(WeContentTalk::getId, talkId).eq(WeContentTalk::getDelFlag, 0));
        List<WeMaterial> materials = iWeMaterialService.getWeMaterialListByTalkId(talkId);
        Long categoryId = weContentTalk.getCategoryId();
        if (categoryId == 1L) {
            weContentTalk.setCategoryName("默认分组");
        } else {
            WeCategory weCategory = weCategoryService.getById(categoryId);
            if (ObjectUtil.isNotEmpty(weCategory)) {
                weContentTalk.setCategoryName(weCategory.getName());
            }
        }
        weContentTalk.setWeMaterialList(materials);
        return AjaxResult.success(weContentTalk);
    }

    @GetMapping("/detail/getTalkMaterial")
    @Operation(summary = "话术素材明细")
    public AjaxResult getTalkMaterial(ContentDetailQuery contentDetailQuery) {
        List<WeMaterialAnalyseVo> weMaterialAnalyseVos = iWeMaterialService.selectMaterialsByTalkId(contentDetailQuery);
//        //TODO 代码优化
//        //获取话术关联的素材
//        LambdaQueryWrapper<WeTalkMaterial> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(WeTalkMaterial::getTalkId, contentDetailQuery.getTalkId());
//        List<WeTalkMaterial> list = weTalkMaterialService.list(queryWrapper);
//
//        List<WeMaterialAnalyseVo> weMaterialAnalyseVos = new ArrayList<>();
//
//        if (list != null && list.size() > 0) {
//            //遍历列表
//            for (WeTalkMaterial weTalkMaterial : list) {
//                QueryWrapper<WeMaterial> weMaterialQueryWrapper = new QueryWrapper<>();
//                weMaterialQueryWrapper.lambda().select(WeMaterial::getId,WeMaterial::getMaterialName,WeMaterial::getMaterialUrl,WeMaterial::getMediaType);
//                weMaterialQueryWrapper.lambda().eq(WeMaterial::getId, weTalkMaterial.getMaterialId());
//                WeMaterial weMaterial = weMaterialService.getOne(weMaterialQueryWrapper);
//                WeMaterialAnalyseVo weMaterialAnalyseVo = BeanUtil.copyProperties(weMaterial, WeMaterialAnalyseVo.class);
//
//                LambdaQueryWrapper<WeContentSendRecord> sendRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
//                sendRecordLambdaQueryWrapper.eq(WeContentSendRecord::getTalkId, contentDetailQuery.getTalkId());
//                sendRecordLambdaQueryWrapper.eq(WeContentSendRecord::getContentId, weTalkMaterial.getMaterialId());
//                sendRecordLambdaQueryWrapper.apply(StringUtils.isNotBlank(contentDetailQuery.getBeginTime()), "DATE_FORMAT(send_time,'%Y-%m-%d')>=DATE_FORMAT('" + contentDetailQuery.getBeginTime() + "','%Y-%m-%d')");
//                sendRecordLambdaQueryWrapper.apply(StringUtils.isNotBlank(contentDetailQuery.getEndTime()), "DATE_FORMAT(send_time,'%Y-%m-%d')<=DATE_FORMAT('" + contentDetailQuery.getEndTime() + "','%Y-%m-%d')");
//                int count = weContentSendRecordService.count(sendRecordLambdaQueryWrapper);
//                //发送总次数
//                weMaterialAnalyseVo.setSendTotalNum(count);
//
//                LambdaQueryWrapper<WeContentViewRecord> viewRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
//                viewRecordLambdaQueryWrapper.eq(WeContentViewRecord::getTalkId, contentDetailQuery.getTalkId());
//                viewRecordLambdaQueryWrapper.eq(WeContentViewRecord::getContentId, weTalkMaterial.getMaterialId());
//                viewRecordLambdaQueryWrapper.apply(StringUtils.isNotBlank(contentDetailQuery.getBeginTime()), "DATE_FORMAT(view_time,'%Y-%m-%d')>=DATE_FORMAT('" + contentDetailQuery.getBeginTime() + "','%Y-%m-%d')");
//                viewRecordLambdaQueryWrapper.apply(StringUtils.isNotBlank(contentDetailQuery.getEndTime()), "DATE_FORMAT(view_time,'%Y-%m-%d')<=DATE_FORMAT('" + contentDetailQuery.getEndTime() + "','%Y-%m-%d')");
//                List<WeContentViewRecord> viewRecords = weContentViewRecordService.list(viewRecordLambdaQueryWrapper);
//                if (viewRecords != null && viewRecords.size() > 0) {
//                    //查看总次数
//                    weMaterialAnalyseVo.setViewTotalNum(viewRecords.size());
//                    //查看总人数
//                    Set<String> collect = viewRecords.stream().map(o -> o.getViewOpenid()).collect(Collectors.toSet());
//                    weMaterialAnalyseVo.setViewByTotalNum(collect.size());
//                } else {
//                    weMaterialAnalyseVo.setViewTotalNum(0);
//                    weMaterialAnalyseVo.setViewByTotalNum(0);
//                }
//                weMaterialAnalyseVos.add(weMaterialAnalyseVo);
//            }
//        }
        return AjaxResult.success(weMaterialAnalyseVos);
    }
}
