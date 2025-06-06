package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.WeChatSide;
import java.util.List;

/**
 * 聊天工具侧边栏
 * @author kewen
 */
public interface IWeChatSideService extends IService<WeChatSide> {

    /**
     * 群发侧边栏列表
     *
     * @return {@link WeChatSide}s
     */
     List<WeChatSide> chatSides(String h5);

    /**
     * 更新侧边栏信息
     *
     * @param weChatSide 侧边栏信息
     * @return 结果
     */
     int updateWeChatSide(WeChatSide weChatSide);

}
