package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeTrackMaterialPrivacyAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeTrackMaterialPrivacyAuth;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeTrackMaterialPrivacyAuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 轨迹素材隐私政策客户授权
 *
 * @author danmo
 * @since 2022-04-02 13:35:13
 */
@Slf4j
@Service
public class WeTrackMaterialPrivacyAuthServiceImpl extends ServiceImpl<WeTrackMaterialPrivacyAuthMapper, WeTrackMaterialPrivacyAuth> implements IWeTrackMaterialPrivacyAuthService {

}
