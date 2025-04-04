package cn.iocoder.yudao.module.system.controller.admin.area;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserSimpleRespVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.area.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.system.service.area.AreaService;

@Tag(name = "管理后台 - 行政区划")
@RestController
@RequestMapping("/system/areadb")
@Validated
public class AreaDbController {

    @Resource
    private AreaService areaService;
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/create")
    @Operation(summary = "创建行政区划")
    @PreAuthorize("@ss.hasPermission('system:area:create')")
    public CommonResult<Integer> createArea(@Valid @RequestBody AreaSaveReqVO createReqVO) {
        return success(areaService.createArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行政区划")
    @PreAuthorize("@ss.hasPermission('system:area:update')")
    public CommonResult<Boolean> updateArea(@Valid @RequestBody AreaSaveReqVO updateReqVO) {
        areaService.updateArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除行政区划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:area:delete')")
    public CommonResult<Boolean> deleteArea(@RequestParam("id") Integer id) {
        areaService.deleteArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行政区划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:area:query')")
    public CommonResult<AreaRespVO> getArea(@RequestParam("id") Integer id) {
        AreaDO area = areaService.getArea(id);
        return success(BeanUtils.toBean(area, AreaRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得行政区划分页")
    @PreAuthorize("@ss.hasPermission('system:area:query')")
    public CommonResult<PageResult<AreaRespVO>> getAreaPage(@Valid AreaPageReqVO pageReqVO) {
        PageResult<AreaDO> pageResult = areaService.getAreaPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行政区划 Excel")
    @PreAuthorize("@ss.hasPermission('system:area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAreaExcel(@Valid AreaPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AreaDO> list = areaService.getAreaPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "行政区划.xls", "数据", AreaRespVO.class,
                BeanUtils.toBean(list, AreaRespVO.class));
    }

    @GetMapping("/getAreaById/{id}")
    public CommonResult<AreaRespVO> getAreaById(@PathVariable("id") Integer id) {
        return success(areaService.getAreaById(id));
    }

    @GetMapping("/getChildListById")
    public CommonResult<List<AreaRespVO>> getChildListById(@NotNull(message = "ID不允许为空") Integer id) {
        List<AreaRespVO> list = areaService.getChildListById(id);
        return success(list);
    }

    @GetMapping("/list")
    public CommonResult<List> listTree(AreaPageReqVO sysArea) {

//        List<Tree<Integer>> areaList = redisTemplate.opsForValue().get("area");
//        if(CollectionUtil.isNotEmpty(areaList)){
//            return success(areaList);
//        }

        List<AreaRespVO> list = areaService.getList(sysArea);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }

        List<TreeNode<Integer>> treeNodes = list.stream().map(area -> {
            return new TreeNode<>(area.getId(), area.getParentId(), area.getExtName(), area.getExtId());
        }).collect(Collectors.toList());
        List<Tree<Integer>> treeList = TreeUtil.build(treeNodes, 0);

//        if(CollectionUtil.isNotEmpty(treeList)){
//            redisService.setCacheList("area",treeList);
//        }

        return success(treeList);
    }

}