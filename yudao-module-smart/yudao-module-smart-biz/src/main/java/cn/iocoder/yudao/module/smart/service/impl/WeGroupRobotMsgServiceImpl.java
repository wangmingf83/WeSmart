package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.service.IWeGroupRobotInfoService;
import cn.iocoder.yudao.module.smart.service.IWeGroupRobotMsgService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;

import cn.iocoder.yudao.module.common.enums.MessageType;
import cn.iocoder.yudao.module.common.enums.WeMsgTypeEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.http.HttpUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupRobotInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupRobotMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotMsgAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotMsgListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.media.WeMediaVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupRobotMsgMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 群机器人消息表(WeGroupRobotMsg)
 *
 * @author danmo
 * @since 2022-11-08 16:06:01
 */
@Slf4j
@Service
public class WeGroupRobotMsgServiceImpl extends ServiceImpl<WeGroupRobotMsgMapper, WeGroupRobotMsg> implements IWeGroupRobotMsgService {

    @Lazy
    @Autowired
    private IWeGroupRobotInfoService weGroupRobotInfoService;

    @Autowired
    private IWeMaterialService weMaterialService;

    @Override
    public List<WeGroupRobotMsg> getMsgList(WeRobotMsgListQuery query) {
        if (Objects.isNull(query.getRobotId())) {
            throw new WeComException("机器人ID不能为空");
        }
        return list(new LambdaQueryWrapper<WeGroupRobotMsg>()
                .eq(WeGroupRobotMsg::getRobotId, query.getRobotId())
                .eq(WeGroupRobotMsg::getDelFlag, 0)
                .like(StringUtils.isNotEmpty(query.getTitle()), WeGroupRobotMsg::getMsgTitle, query.getTitle())
                .ge(Objects.nonNull(query.getStartTime()), WeGroupRobotMsg::getSendTime, query.getStartTime())
                .le(Objects.nonNull(query.getEndTime()), WeGroupRobotMsg::getSendTime, query.getEndTime())
                .orderByDesc(BaseEntity::getCreateTime));
    }

    @Override
    public void addRobotMsg(WeRobotMsgAddQuery query) {
        WeGroupRobotInfo robotInfo = weGroupRobotInfoService.getById(query.getRobotId());
        if (Objects.isNull(robotInfo)) {
            throw new WeComException("机器人信息不存在");
        }
        if (StringUtils.isEmpty(robotInfo.getWebHookUrl())) {
            throw new WeComException("机器人链接为空");
        }
        WeGroupRobotMsg weGroupRobotMsg = new WeGroupRobotMsg();
        BeanUtil.copyProperties(query.getWeMessageTemplate(), weGroupRobotMsg);
        weGroupRobotMsg.setRobotId(query.getRobotId());
        weGroupRobotMsg.setMsgTitle(query.getMsgTitle());
        weGroupRobotMsg.setSendTime(new Date());
        if (save(weGroupRobotMsg)) {
            JSONObject msgObj = new JSONObject();
            WeMessageTemplate messageTemplate = query.getWeMessageTemplate();
            if (ObjectUtil.equal(WeMsgTypeEnum.TEXT.getMessageType(), messageTemplate.getMsgType())) {
                JSONObject textObj = new JSONObject();
                textObj.put("content", messageTemplate.getContent());
                msgObj.put("msgtype", WeMsgTypeEnum.TEXT.getMessageType());
                msgObj.put(WeMsgTypeEnum.TEXT.getMessageType(), textObj);
            } else if (ObjectUtil.equal(WeMsgTypeEnum.IMAGE.getMessageType(), messageTemplate.getMsgType())) {
                JSONObject imgObj = new JSONObject();
                if (StringUtils.isEmpty(messageTemplate.getPicUrl())) {
                    throw new WeComException("图片不能为空");
                }
                try {
                    String suffix = FileUtil.getSuffix(messageTemplate.getPicUrl());
                    URL url = new URL(messageTemplate.getPicUrl());
                    BufferedImage read = ImgUtil.read(url);
                    byte[] bytes = ImgUtil.toBytes(read, suffix);
                    String md5 = DigestUtils.md5Hex(bytes);
                    String base64 = Base64.encode(bytes);
                    imgObj.put("base64", base64);
                    imgObj.put("md5", md5);
                } catch (Exception e) {
                    log.info("发送失败，获取图片信息异常 msg:{}", JSONObject.toJSONString(messageTemplate), e);
                    throw new WeComException("发送失败，获取图片信息异常");
                }
                msgObj.put("msgtype", WeMsgTypeEnum.IMAGE.getMessageType());
                msgObj.put(WeMsgTypeEnum.IMAGE.getMessageType(), imgObj);
            } else if (ObjectUtil.equal(WeMsgTypeEnum.NEWS.getMessageType(), messageTemplate.getMsgType())) {
                JSONObject newsObj = new JSONObject();
                JSONObject articlesObj = new JSONObject();
                articlesObj.put("title", messageTemplate.getTitle());
                articlesObj.put("description", messageTemplate.getDescription());
                articlesObj.put("url", messageTemplate.getLinkUrl());
                articlesObj.put("picurl", messageTemplate.getPicUrl());
                newsObj.put("articles", CollectionUtil.newArrayList(articlesObj));
                msgObj.put("msgtype", WeMsgTypeEnum.NEWS.getMessageType());
                msgObj.put(WeMsgTypeEnum.NEWS.getMessageType(), newsObj);
            } else if (ObjectUtil.equal(WeMsgTypeEnum.FILE.getMessageType(), messageTemplate.getMsgType())) {
                String key = StringUtils.substringAfter(robotInfo.getWebHookUrl(), "key=");
                WeMediaVo weMedia = weMaterialService.uploadWebhookMaterial(key,messageTemplate.getFileUrl()
                        , MessageType.FILE.getMessageType()
                        , FileUtil.getName(messageTemplate.getFileUrl()));
                JSONObject fileObj = new JSONObject();
                fileObj.put("media_id", weMedia.getMediaId());
                msgObj.put("msgtype", WeMsgTypeEnum.FILE.getMessageType());
                msgObj.put(WeMsgTypeEnum.FILE.getMessageType(), fileObj);
            }
            String respMsg = HttpUtils.sendPost(robotInfo.getWebHookUrl(), msgObj.toJSONString());
            JSONObject resultJSON = JSONObject.parseObject(respMsg);
            if (resultJSON.getIntValue("errcode") == 0) {
                log.info("消息发送成功!");
                weGroupRobotMsg.setStatus(2);
            } else {
                weGroupRobotMsg.setStatus(3);
                log.info("消息发送失败, 错误信息如下: " + resultJSON.getString("errmsg"));
            }
            updateById(weGroupRobotMsg);
        }
    }
}
