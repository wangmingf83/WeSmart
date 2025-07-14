package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeChatSideService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.iocoder.yudao.module.smart.dal.dataobject.side.WeChatSide;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeChatSideMapper;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 聊天工具侧边栏
 * @author kewen
 */
@Service
public class WeChatSideServiceImpl extends ServiceImpl<WeChatSideMapper, WeChatSide> implements IWeChatSideService {



    @Override
    public List<WeChatSide> chatSides(String h5) {
        return this.baseMapper.selectWeChatSides(h5);
    }

    @Override
    public int updateWeChatSide(WeChatSide weChatSide) {
        return this.baseMapper.updateWeChatSideById(weChatSide);
    }

}
