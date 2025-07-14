package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeMsgTlp;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.dto.WeMsgTlpDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.query.WeMsgTlpAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.query.WeMsgTlpQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.vo.WeMsgTlpVo;

import java.util.List;

/**
 * 欢迎语模板表(WeMsgTlp)
 *
 * @author danmo
 * @since 2022-03-28 10:21:24
 */
public interface IWeMsgTlpService extends IService<WeMsgTlp> {

    /**
     * 新增欢迎语模板
     * @param weMsgTlp
     */
    void addMsgTlp(WeMsgTlp weMsgTlp);

    /**
     * 修改欢迎语模板
     * @param query
     */
    void updateMsgTlp(WeMsgTlpAddQuery query);

    /**
     * 删除欢迎语模板
     * @param ids
     */
    void delMsgTlp(List<Long> ids);

    /**
     * 获取欢迎语模板详情
     *
     * @param id
     */
    WeMsgTlpVo getInfo(Long id);

    /**
     * 获取欢迎语模板列表
     * @param query
     * @return
     */
    List<WeMsgTlpVo> getList(WeMsgTlpQuery query);

    List<WeMaterial> preview(Long tlpId);

    void updateCategory(WeMsgTlpQuery query);

    /**
     * 保存或更新
     * @param weMsgTlpDto
     */
    void addOrUpdate(WeMsgTlpDto weMsgTlpDto);


    /**
     * 群欢迎语同步至企业微信
     * @param weMsgTlpDto
     */
    void synchGroupWelcomMsg(WeMsgTlpDto weMsgTlpDto,Long tlpId);
}
