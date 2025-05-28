package cn.iocoder.yudao.module.smart.service;

import cn.iocoder.yudao.module.smart.core.page.PageDomain;
import com.baomidou.mybatisplus.extension.service.IService;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerAddGroupVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.query.WeGroupChatQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.LinkGroupChatListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.groupchat.WeGroupChatListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.groupchat.WeGroupChatListVo;

import java.util.List;

/**
 * 企业微信群(WeGroup)
 *
 * @author danmo
 * @since 2022-04-02 13:35:13
 */
public interface IWeGroupService extends IService<WeGroup> {

    /**
     * 获取群列表
     * @param query
     */
    List<LinkGroupChatListVo> getPageList(WeGroupChatQuery query, PageDomain pageDomain);

    /**
     * 获取总数
     * @param query
     * @param pageDomain
     * @return
     */
    long countWeGroupListIds(WeGroupChatQuery query);

    /**
     * 获取群列表
     * @param query
     */
    List<LinkGroupChatListVo> getPageList(WeGroupChatQuery query);

    /**
     *  应用获取客群数据
     * @param query
     * @return
     */
    List<LinkGroupChatListVo> selectWeGroupListByApp(WeGroupChatQuery query);

    /**
     * 获取群详情
     * @param id
     */
    LinkGroupChatListVo getInfo(Long id);

    LinkGroupChatListVo getInfo(String chatId);

    /**
     * 同步客户群
     */
    void synchWeGroup();

    /**
     * 同步客户群
     * @param msg
     */
    void synchWeGroupHandler(String msg);





//    /**
//     * 跟进条件同步客群
//     * @param chatListQuery
//     * @return
//     */
//    List<WeGroupChatListVo.GroupChat> synchWeGroup(WeGroupChatListQuery chatListQuery);



    public List<WeGroup> findGroupInfoFromWechat(WeGroupChatListQuery chatListQuery);





    /**
     * 从企业微信端获取的群信息
     * @param groupChatList
     * @param chatListQuery
     */
    void getGroupChatList(List<WeGroupChatListVo.GroupChat> groupChatList, WeGroupChatListQuery chatListQuery);

    /**
     * 创建客群
     * @param chatId
     * @param corpId
     * @param isCallBack 是否回掉 true回掉调用该方法,false非回掉触发改方法
     */
    void createWeGroup(String chatId, String corpId,boolean isCallBack);

    /**
     * 解散客群
     * @param chatId
     */
    void deleteWeGroup(String chatId);


    /**
     * 获取客户添加的群
     * @param userId
     * @param externalUserid
     * @return
     */
    List<WeCustomerAddGroupVo> findWeGroupByCustomer(String userId, String externalUserid);

    /**
     * 成员入群
     * @param chatId 群ID
     * @param joinScene 入群方式
     * @param memChangeCnt 成员变更数量
     * @param memeberList 入群成员id
     */
    void addMember(String chatId, Integer joinScene, Integer memChangeCnt,List<String> memeberList);

    /**
     * 成员退群
     * @param chatId 群ID
     * @param quitScene 入群方式
     * @param memChangeCnt 成员变更数量
     * @param memeberList 退群成员集合
     */
    void delMember(String chatId, Integer quitScene, Integer memChangeCnt,List<String> memeberList);

    /**
     * 群信息变更
     * @param chatId 群ID
     * @param updateDetail 变更类型
     */
    void changeGroup(String chatId, String updateDetail);


    /**
     * 根据群员id获取群id
     * @param chatUserId
     * @param state
     * @return
     */
    List<WeGroup> findGroupByUserId(String chatUserId,String state);


    /**
     * 群成员id查询相关群
     * @param query
     * @return
     */
    List<LinkGroupChatListVo> selectChatByMember(WeGroupChatQuery query);




}
