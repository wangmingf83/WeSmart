package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import cn.iocoder.yudao.module.smart.service.IWeRedEnvelopesService;
import cn.iocoder.yudao.module.smart.service.IWeUserRedEnvelopsLimitService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.constant.HttpStatus;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.RedEnvelopesType;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.poi.ExcelUtil;
import cn.iocoder.yudao.module.common.utils.poi.LwExcelUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeRedEnvelopes;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeRedEnvelopesLimit;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeRedEnvelopesRecord;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeUserRedEnvelopsLimit;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.query.H5RedEnvelopesParmQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.query.WeRedEnvelopeListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.vo.RedEnvelopesBaseInfoVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.vo.WeCutomerRedEnvelopesVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.vo.WeGroupRedEnvelopesVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 红包相关接口
 */
@Tag(name = "红包接口管理")
@RestController
@RequestMapping(value = "/RedEnvelopes")
public class WeRedEnvelopesController extends BaseController {

    @Autowired
    private IWeRedEnvelopesService iWeRedEnvelopesService;


    @Autowired
    private IWeUserRedEnvelopsLimitService iWeUserRedEnvelopsLimitService;


    @Autowired
    private IWeCorpAccountService iWeCorpAccountService;

    /**********************************************************
     *******************************企业红包start***************
     **********************************************************/

    /**
     * 红包限额获取
     * @return
     */
    @GetMapping("/findLimitRedEnvelopes")
    public AjaxResult<WeRedEnvelopesLimit> findLimitRedEnvelopes(){
        return AjaxResult.success(
                iWeRedEnvelopesService.findLimitRedEnvelopes()
        );
    }


    /**
     * 设置红包限额
     * @param envelopesLimit
     * @return
     */
    @PostMapping("/limitRedEnvelopes")
    public AjaxResult limitRedEnvelopes(@RequestBody WeRedEnvelopesLimit envelopesLimit){
        iWeRedEnvelopesService.limitRedEnvelopes(envelopesLimit);
        return AjaxResult.success();
    }


    /**
     * 新建或编辑红包
     * @return
     */
    @PostMapping("/addorUpdateRedEnvelopes")
    public AjaxResult addorUpdateRedEnvelopes(@RequestBody WeRedEnvelopes weRedEnvelopes){
        iWeRedEnvelopesService.saveOrUpdate(weRedEnvelopes);
        return AjaxResult.success();
    }


    /**
     * 获取企业红包列表
     * @param weRedEnvelope
     * @return
     */
    @GetMapping("/redEnvelopes")
    public TableDataInfo redEnvelopes(WeRedEnvelopes weRedEnvelope){
        startPage();
        return getDataTable(
                iWeRedEnvelopesService.list(new LambdaQueryWrapper<WeRedEnvelopes>()
                        .eq(weRedEnvelope.getStatus() != null,WeRedEnvelopes::getStatus,weRedEnvelope.getStatus())
                        .eq(weRedEnvelope.getSceneType() !=null,WeRedEnvelopes::getSceneType,weRedEnvelope.getSceneType())
                        .eq(WeRedEnvelopes::getRedEnvelopesType, RedEnvelopesType.COMPANY_RED_ENVELOPES.getType())
                        .orderByDesc(WeRedEnvelopes::getCreateTime)
                )
        );
    }

    /**
     * 启用或者停用红包 0:启用;1:停用
     * @param weRedEnvelope
     * @return
     */
    @PutMapping("/startOrStopRedEnvelope")
    public AjaxResult startOrStopRedEnvelope(@RequestBody WeRedEnvelopes weRedEnvelope){

        iWeRedEnvelopesService.updateById(weRedEnvelope);

        return AjaxResult.success();
    }


    /**
     * 删除红包
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteRedEnvelop/{ids}")
    public AjaxResult deleteRedEnvelop(@PathVariable String[] ids){
        iWeRedEnvelopesService.removeByIds(ListUtil.toList(ids));
        return AjaxResult.success();
    }

    /**********************************************************
     *******************************企业红包end*****************
     **********************************************************/


    /**********************************************************
     *******************************员工限额start***************
     **********************************************************/

    /**
     * 单个员工限额新增或编辑
     * @param redEnvelopsLimit
     * @return
     */
    @PostMapping("/addOrUpdateUserRedEnvelopsLimit")
    public AjaxResult addOrUpdateUserRedEnvelopsLimit(@RequestBody WeUserRedEnvelopsLimit redEnvelopsLimit){
        StringBuilder sb=new StringBuilder();
        String weUserId = redEnvelopsLimit.getWeUserId();
        if(StringUtils.isNotEmpty(weUserId)){
            WeRedEnvelopesLimit limitRedEnvelopes = iWeRedEnvelopesService.findLimitRedEnvelopes();
            List<WeUserRedEnvelopsLimit> redEnvelopsLimits=new ArrayList<>();
            String[] split = weUserId.split(",");
            List<String> list = Arrays.asList(split);
            int singleCustomerReceiveMoney = limitRedEnvelopes.getSingleCustomerReceiveMoney();
            Integer customerReceiveMoney = redEnvelopsLimit.getSingleCustomerReceiveMoney();
            if(singleCustomerReceiveMoney < customerReceiveMoney * list.size()){
                return AjaxResult.error("员工红包金额不能超过企业红包限额");
            }

            List<WeUserRedEnvelopsLimit> weUserRedEnvelopsLimitList= iWeUserRedEnvelopsLimitService.list(
                    new LambdaQueryWrapper<WeUserRedEnvelopsLimit>().in(WeUserRedEnvelopsLimit::getWeUserId, list));

            if(ObjectUtil.isNotEmpty(weUserRedEnvelopsLimitList)){
                return AjaxResult.error("员工不允许有重复");
            }
            list.forEach(k->{
                WeUserRedEnvelopsLimit copyRedEnvelops=new WeUserRedEnvelopsLimit();
                BeanUtil.copyProperties(redEnvelopsLimit,copyRedEnvelops,true);
                copyRedEnvelops.setWeUserId(k);
                redEnvelopsLimits.add(copyRedEnvelops);

            });
            iWeUserRedEnvelopsLimitService.saveOrUpdateBatch(redEnvelopsLimits);
        }

        return AjaxResult.success(sb.toString());
    }


    /**
     * 批量更新或编辑员工限额
     * @param redEnvelopsLimit
     * @return
     */
    @PostMapping("/batchUpdateUserRedEnvelopsLimit")
    public AjaxResult batchUpdateUserRedEnvelopsLimit(@RequestBody WeUserRedEnvelopsLimit redEnvelopsLimit){
        if(StringUtils.isNotEmpty(redEnvelopsLimit.getIds())){
            List<WeUserRedEnvelopsLimit> redEnvelopsLimits=new ArrayList<>();
            Arrays.asList(redEnvelopsLimit.getIds().split(",")).stream().forEach(k->{
                redEnvelopsLimits.add(
                        WeUserRedEnvelopsLimit.builder()
                                .id(Long.parseLong(k))
                                .singleCustomerReceiveMoney(redEnvelopsLimit.getSingleCustomerReceiveMoney())
                                .singleCustomerReceiveNum(redEnvelopsLimit.getSingleCustomerReceiveNum())
                                .build()
                );
            });

            iWeUserRedEnvelopsLimitService.updateBatchById(redEnvelopsLimits);
        }

        return AjaxResult.success();
    }


    /**
     * 员工限额列表
     * @param userId
     * @return
     */
    @GetMapping("/findLimitUserRedEnvelops")
    public TableDataInfo findLimitUserRedEnvelops(String userId){
        startPage();
        return getDataTable(
                iWeUserRedEnvelopsLimitService.findLimitUserRedEnvelops(userId)
        );
    }


    /**
     * 删除员工限额
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteLimitUserRedEnvelops/{ids}")
    public AjaxResult deleteLimitUserRedEnvelops(@PathVariable String[] ids){
        iWeUserRedEnvelopsLimitService.removeByIds(ListUtil.toList(ids));
        return AjaxResult.success();
    }

    /**********************************************************
     *******************************员工限额end*****************
     **********************************************************/

    /**********************************************************
     *******************************发放记录start***************
     **********************************************************/
    /**
     * 发放记录-发放客户
     * @param redEnvelopesRecord
     * @return
     */
    @GetMapping("/findRedEnveForUser")
    public TableDataInfo findRedEnveForUser(WeRedEnvelopesRecord redEnvelopesRecord){
        startPage();
        return getDataTable(
                iWeRedEnvelopesService.findRedEnveForUser(redEnvelopesRecord)
        );
    }


    /**
     * 发放记录-发放客户导出
     * @param redEnvelopesRecord
     * @return
     */
    @GetMapping("/exportRedEnveForUser")
    public void exportRedEnveForUser(WeRedEnvelopesRecord redEnvelopesRecord){
        List<WeCutomerRedEnvelopesVo> redEnvelopesVos=iWeRedEnvelopesService.findRedEnveForUser(redEnvelopesRecord);
        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(), WeCutomerRedEnvelopesVo.class,redEnvelopesVos,"客户红包发放记录"
        );
    }


    /**
     *  发放记录-发放客户群
     * @param redEnvelopesRecord
     * @return
     */
    @GetMapping("/findRedEnveForGroup")
    public TableDataInfo findRedEnveForGroup(WeRedEnvelopesRecord redEnvelopesRecord){
        startPage();
        return getDataTable(
                iWeRedEnvelopesService.findRedEnveForGroup(redEnvelopesRecord)
        );

    }

    /**
     *  发放记录-发放客户群导出
     * @param redEnvelopesRecord
     * @return
     */
    @GetMapping("/exportRedEnveForGroup")
    public void exportRedEnveForGroup(WeRedEnvelopesRecord redEnvelopesRecord){
        List<WeGroupRedEnvelopesVo> redEnvelopesVos=iWeRedEnvelopesService.findRedEnveForGroup(redEnvelopesRecord);

        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(), WeGroupRedEnvelopesVo.class,redEnvelopesVos,"红包发放客户群"
        );
    }


    /**
     * 获取群成员领取红包列表
     * @param chatId
     * @return
     */
    @GetMapping("/findRedEnveForGroupUser")
    public TableDataInfo findRedEnveForGroupUser(String chatId,String orderNo){
        startPage();

        return getDataTable(
                iWeRedEnvelopesService.findRedEnveForGroupUser(chatId,orderNo)
        );
    }
    /**********************************************************
     *******************************发放记录end*****************
     **********************************************************/



    /**********************************************************
     *******************************支出统计start***************
     **********************************************************/
    /**
     * 支出统计头部
     * @return
     */
    @GetMapping("/countTab")
    public AjaxResult countTab(){

        return AjaxResult.success(
                iWeRedEnvelopesService.countTab()
        );
    }


    /**
     * 支出统计-支出趋势
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/countLineChart")
    public AjaxResult countLineChart(String beginTime,String endTime){

        return AjaxResult.success(
                iWeRedEnvelopesService.countLineChart(beginTime,endTime)
        );
    }


    /**
     * 支出统计-支出记录
     * @return
     */
    @GetMapping("/findRecordGroupByUserId")
    public TableDataInfo findRecordGroupByUserId(String beginTime,String endTime){
        startPage();

        return getDataTable(
                iWeRedEnvelopesService.findRecordGroupByUserId(beginTime,endTime)
        );
    }


    /**********************************************************
     *******************************支出统计end*****************
     **********************************************************/


    /**********************************************************
     *******************************移动端start*****************
     **********************************************************/


    /**
     * 为客户发送个人红包
     * @return
     */
    @PostMapping("/sendReEnvelopesToCustomer")
    public AjaxResult sendPersonReEnvelopesToCustomer(@RequestBody H5RedEnvelopesParmQuery parmDto)  {
        return AjaxResult.success(
                iWeRedEnvelopesService.createCustomerRedEnvelopesOrder(parmDto.getRedenvelopesId(),parmDto.getRedEnvelopeAmount(),
                        parmDto.getRedEnvelopeName(),1,
                        parmDto.getSendUserId(),1, parmDto.getExternalUserid())
        );
    }


    /**
     *  为客户发送企业红包
     * @param parmDto
     * @return
     */
    @PostMapping("/sendCompanyEnvelopesToCustomer")
    public AjaxResult sendCompanyEnvelopesToCustomer(@RequestBody H5RedEnvelopesParmQuery parmDto){

        WeRedEnvelopes weRedEnvelopes
                = iWeRedEnvelopesService.getById(parmDto.getRedenvelopesId());
        if(null == weRedEnvelopes){
            return AjaxResult.error(HttpStatus.NOT_ORTHER_IMPLEMENTED,"当前企业红包不存在");
        }

        String returnMsg = iWeRedEnvelopesService.checkRedEnvelopesTplLimit();

        if(StringUtils.isNotEmpty(returnMsg)){
            return AjaxResult.error(HttpStatus.NOT_ORTHER_IMPLEMENTED,returnMsg);
        }


        return AjaxResult.success(
                iWeRedEnvelopesService.createCustomerRedEnvelopesOrder(parmDto.getRedenvelopesId(),weRedEnvelopes.getMoney(),
                        weRedEnvelopes.getName(),1,
                        parmDto.getSendUserId(),2, parmDto.getExternalUserid())
        );
    }


    /**
     *  为客户群发送个人红包
     * @param parmDto
     * @return
     */
    @PostMapping("/sendPersonReEnvelopesToGroup")
    public AjaxResult sendPersonReEnvelopesToGroup(@RequestBody H5RedEnvelopesParmQuery parmDto){

        return AjaxResult.success(

                iWeRedEnvelopesService.createGroupRedEnvelopesOrder(parmDto.getRedenvelopesId(),
                        parmDto.getRedEnvelopeAmount(),parmDto.getRedEnvelopeName(),
                        parmDto.getRedEnvelopeNum(),parmDto.getChatId(),parmDto.getSendUserId(),parmDto.getRedEnvelopesType(),1
                )
        );
    }


    /**
     *  为客户群发送企业红包
     * @param parmDto
     * @return
     */
    @PostMapping("/sendCompanyEnvelopesToGroup")
    public AjaxResult sendCompanyEnvelopesToGroup(@RequestBody H5RedEnvelopesParmQuery parmDto){

        WeRedEnvelopes weRedEnvelopes
                = iWeRedEnvelopesService.getById(parmDto.getRedenvelopesId());
        if(null == weRedEnvelopes){
            return AjaxResult.error(HttpStatus.NOT_ORTHER_IMPLEMENTED,"当前企业红包不存在");
        }

        String returnMsg = iWeRedEnvelopesService.checkRedEnvelopesTplLimit();

        if(StringUtils.isNotEmpty(returnMsg)){
            return AjaxResult.error(HttpStatus.NOT_ORTHER_IMPLEMENTED,returnMsg);
        }

        return AjaxResult.success(
                iWeRedEnvelopesService.createGroupRedEnvelopesOrder(parmDto.getRedenvelopesId(),
                        weRedEnvelopes.getMoney(),weRedEnvelopes.getName(),parmDto.getRedEnvelopeNum(),parmDto.getChatId(),parmDto.getSendUserId(),parmDto.getRedEnvelopesType(),2
                )
        );

    }


    /**
     * 获取企业红包模版
     * @param sceneType 1:客户 2:客群
     * @return
     */
    @GetMapping("/findCompanyRedEnvelopes")
    public AjaxResult findCompanyRedEnvelopes(Integer  sceneType){
        return AjaxResult.success(
                iWeRedEnvelopesService.list(
                        new LambdaQueryWrapper<WeRedEnvelopes>()
                                .eq(WeRedEnvelopes::getStatus,0)
                                .in(WeRedEnvelopes::getSceneType,ListUtil.toList(sceneType,3))
                )
        );
    }






    /**
     * 红包领取
     * @param orderNo
     * @param openId
     * @param receiveName
     * @param avatar
     * @return
     * @throws Exception
     */
    @GetMapping("/receiveRedEnvelopes")
    public AjaxResult receiveRedEnvelopes(String orderNo,String openId,String receiveName,String avatar) throws Exception {

        String returnMsg = iWeRedEnvelopesService.customerReceiveRedEnvelopes(orderNo, openId,receiveName,avatar);

        if(StringUtils.isNotEmpty(returnMsg)){
            return AjaxResult.error(HttpStatus.NOT_IMPLEMENTED,returnMsg);
        }
        return AjaxResult.success(
                returnMsg
        );
    }


    /**
     * 红包领取列表
     * @param orderNo
     * @param openId
     * @param chatId
     * @return
     */
    @GetMapping("/receiveRedEnvelopesLists")
    public AjaxResult receiveRedEnvelopesLists(String orderNo,String openId,String chatId){

        return AjaxResult.success(
                iWeRedEnvelopesService.detailDto(orderNo,openId,chatId)
        );
    }

    /**
     * 获取红包基础信息
     * @return
     */
    @GetMapping("/findRedEnvelopesInfo")
    public AjaxResult<RedEnvelopesBaseInfoVo> findRedEnvelopesInfo(){
        RedEnvelopesBaseInfoVo baseInfo=RedEnvelopesBaseInfoVo.builder().build();
        WeCorpAccount validWeCorpAccount = iWeCorpAccountService.getCorpAccountByCorpId(null);
        if(validWeCorpAccount !=null){
            baseInfo.setLogo(validWeCorpAccount.getLogoUrl());
            baseInfo.setCorpName(validWeCorpAccount.getCompanyName());
//            baseInfo.setWeAppId(validWeCorpAccount.getWxAppId());
//            baseInfo.setWeAppSecret(baseInfo.getWeAppSecret());
        }
        return AjaxResult.success(baseInfo);
    }


    /**
     * 校验客户是否领取红包
     * @param orderNo 订单id
     * @param openId 客户id
     * @return
     */
    @GetMapping("/checkRecevieRedEnvelopes")
    public AjaxResult checkRecevieRedEnvelopes(String orderNo,String openId){

        return AjaxResult.success(
                iWeRedEnvelopesService.checkRecevieRedEnvelopes(orderNo,openId)
        );

    }


    /**********************************************************
     *******************************移动端end*******************
     **********************************************************/


    @Operation(summary = "红包列表查询",method = "GET")
    @GetMapping("/list")
    public TableDataInfo<List<WeRedEnvelopes>> getList(WeRedEnvelopeListQuery query){
        super.startPage();
        List<WeRedEnvelopes> list =  iWeRedEnvelopesService.getList(query);
        return getDataTable(list);
    }

}
