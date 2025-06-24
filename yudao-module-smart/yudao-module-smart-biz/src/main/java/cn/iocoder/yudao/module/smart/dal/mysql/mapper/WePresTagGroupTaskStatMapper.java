package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.WePresTagGroupTaskStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WePresTagGroupTaskStatMapper extends BaseMapper<WePresTagGroupTaskStat> {

    int batchSave(List<WePresTagGroupTaskStat> stats);

    /**
     * 获取老客标签建群任务的消息发送结果
     *
     * @param stat 过滤条件
     * @return 发送结果列表
     */
    @DataScope(type = "2", value = @DataColumn(alias = "wgmsr", name = "create_by_id", userid = "user_id"))
    List<WePresTagGroupTaskStat> cropSendResultList(WePresTagGroupTaskStat stat);

    /**
     * 获取老客标签建群任务的消息发送结果
     *
     * @param stat 过滤条件
     * @return 发送结果列表
     */
    @DataScope(type = "2", value = @DataColumn(alias = "st", name = "create_by_id", userid = "user_id"))
    List<WePresTagGroupTaskStat> singleSendResultList(WePresTagGroupTaskStat stat);
}
