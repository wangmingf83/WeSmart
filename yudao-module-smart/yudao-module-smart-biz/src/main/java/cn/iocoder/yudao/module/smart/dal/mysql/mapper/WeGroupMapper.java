package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import cn.iocoder.yudao.module.smart.core.page.PageDomain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerAddGroupVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.query.WeGroupChatQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.LinkGroupChatListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 企业微信群(WeGroup)
 *
 * @author danmo
 * @since 2022-04-02 13:35:13
 */
@Repository()
@Mapper
public interface WeGroupMapper extends BaseMapper<WeGroup> {


//    List<Long> getGroupChatIdList(WeGroupChatQuery query);

//    List<LinkGroupChatVo> getGroupChatListByIds(@Param("groupIdList") List<Long> groupIdList);
    @DataScope(type = "2", value = @DataColumn(alias = "wg", name = "owner", userid = "we_user_id"))
    List<String> findWeGroupListIds(@Param("query") WeGroupChatQuery query,@Param("pageDomain") PageDomain pageDomain);


    @DataScope(type = "2", value = @DataColumn(alias = "wg", name = "owner", userid = "we_user_id"))
    long countWeGroupListIds(@Param("query") WeGroupChatQuery query);

    @DataScope(type = "2", value = @DataColumn(alias = "wg", name = "owner", userid = "we_user_id"))
    List<LinkGroupChatListVo> selectWeGroupList(WeGroupChatQuery query);


    List<LinkGroupChatListVo> selectWeGroupListByIds(@Param("ids") List<String> ids);


    List<LinkGroupChatListVo> selectWeGroupListByApp(WeGroupChatQuery query);

    List<WeCustomerAddGroupVo> findWeGroupByCustomer(@Param("userId") String userId, @Param("externalUserid") String externalUserid);

    
    void insertBatch(@Param("weGroups") List<WeGroup> weGroups);

    
    LinkGroupChatListVo selectWeGroupDetail(@Param("chatId") String chatId);

    //根据群成员id获取渠道id
    List<WeGroup> findGroupByUserId(@Param("chatUserId") String chatUserId,@Param("states") String states);

    //根据群成员相关id查询群
     List<LinkGroupChatListVo> selectChatByMember(@Param("query") WeGroupChatQuery query);

}

