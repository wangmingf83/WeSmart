package cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeMakeGroupTagQuery {

    //群聊ID
    private String chatId;

    //标签Id列表
    private List<String> tagIds;
}
