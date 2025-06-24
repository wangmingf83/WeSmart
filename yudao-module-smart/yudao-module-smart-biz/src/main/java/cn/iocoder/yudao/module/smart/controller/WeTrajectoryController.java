package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeCustomerTrajectoryService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerTrajectory;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.WeMaterialVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trajectory")
public class WeTrajectoryController extends BaseController {

    @Autowired
    IWeCustomerTrajectoryService weCustomerTrajectoryService;
    @Resource
    private IWeMaterialService weMaterialService;

    /**
     * 获取企业动态
     *
     * @param trajectoryType
     * @param dataScope      个人数据:false 全部数据(相对于角色定义的数据权限):true
     * @return
     */
    @GetMapping("/findCompanyDynamics")
    public TableDataInfo<WeCustomerTrajectory> findCompanyDynamics(Integer trajectoryType, @RequestParam(defaultValue = "true") boolean dataScope) {
        startPage();
        return getDataTable(
                dataScope ? weCustomerTrajectoryService.findAllTrajectory(
                        WeCustomerTrajectory.builder().trajectoryType(trajectoryType).build()
                ) : weCustomerTrajectoryService.findPersonTrajectory(
                        WeCustomerTrajectory.builder().trajectoryType(trajectoryType).build()
                )
        );
    }


    /**
     * 获取客户轨迹
     *
     * @return
     */
    @GetMapping("/findTrajectory")
    public TableDataInfo<WeCustomerTrajectory> findTrajectory(String externalUserid, String weUserId, Integer trajectoryType) {
        startPage();
        List<WeCustomerTrajectory> list;
        if (trajectoryType != null) {
            list = weCustomerTrajectoryService.list(
                    new LambdaQueryWrapper<WeCustomerTrajectory>()
                            .eq(StringUtils.isNotEmpty(externalUserid), WeCustomerTrajectory::getExternalUseridOrChatid, externalUserid)
                            .eq(StringUtils.isNotEmpty(weUserId), WeCustomerTrajectory::getWeUserId, weUserId)
                            .eq(trajectoryType != null, WeCustomerTrajectory::getTrajectoryType, trajectoryType)
                            .orderByDesc(WeCustomerTrajectory::getCreateTime));
        } else {
            list = weCustomerTrajectoryService.list(
                    new LambdaQueryWrapper<WeCustomerTrajectory>()
                            .eq(StringUtils.isNotEmpty(externalUserid), WeCustomerTrajectory::getExternalUseridOrChatid, externalUserid)
                            .eq(StringUtils.isNotEmpty(weUserId), WeCustomerTrajectory::getWeUserId, weUserId)
                            .ne(WeCustomerTrajectory::getTrajectoryType, 4)
                            .orderByDesc(WeCustomerTrajectory::getCreateTime));
        }


        List<Long> collect = list.stream().map(WeCustomerTrajectory::getMaterialId).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(collect)) {
            List<WeMaterial> weMaterials = weMaterialService.listByIds(collect);
            list.forEach(item -> {
                Optional<WeMaterial> first = weMaterials.stream().filter(o -> o.getId().equals(item.getMaterialId())).findFirst();
                if (first.isPresent()) {
                    WeMaterial weMaterial = first.get();
                    WeMaterialVo weMaterialVo = new WeMaterialVo();
                    weMaterialVo.setId(weMaterial.getId());
                    weMaterialVo.setMaterialUrl(weMaterial.getMaterialUrl());
                    weMaterialVo.setMaterialName(weMaterial.getMaterialName());
                    weMaterialVo.setCoverUrl(weMaterial.getCoverUrl());
                    weMaterialVo.setMediaType(weMaterial.getMediaType());
                    weMaterialVo.setResourceType(weMaterial.getModuleType().toString());
                    item.setWeMaterialVo(weMaterialVo);
                }
            });
        }

        return getDataTable(list);
    }


}
