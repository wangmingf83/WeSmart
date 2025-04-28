package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.WeChatItem;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.dto.WeChatItemDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.vo.WeChatSideVo;
import java.util.List;

/**
 * 聊天工具侧边栏
 * @author kewen
 */
public interface IWeChatItemService extends IService<WeChatItem> {

    /**
     * 侧边栏抓取素材
     *
     * @param chatItemDto 侧边栏素材
     * @return 结果
     */
     void checkItems(WeChatItemDto chatItemDto);

    /**
     * h5素材列表
     *
     * @param sideId 侧边栏id
     * @return
     */
     List<WeChatSideVo> chatItems(Long sideId, String keyword, String mediaType, String userId);





}
