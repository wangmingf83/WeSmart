package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeFissionNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.WeFissionNotice;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeFissionNoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author robin
* @description 针对表【we_fission_detail(裂变明细表)】的数据库操作Service实现
* @createDate 2023-03-14 14:07:21
*/
@Service
public class WeFissionNoticeServiceImpl extends ServiceImpl<WeFissionNoticeMapper, WeFissionNotice>
    implements IWeFissionNoticeService {

    @Override
    public void physicalDelete(Long fissionId) {
        this.baseMapper.physicalDelete(fissionId);
    }
}




