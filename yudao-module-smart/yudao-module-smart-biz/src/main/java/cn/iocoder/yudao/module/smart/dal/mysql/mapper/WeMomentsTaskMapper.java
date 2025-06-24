package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsTask;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsTaskListRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo.WeMomentsTaskVO;

import java.util.List;

/**
 * 朋友圈
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/06 17:48
 */
public interface WeMomentsTaskMapper extends BaseMapper<WeMomentsTask> {

    /**
     * 朋友圈列表
     *
     * @param request
     * @return {@link List< WeMomentsTaskVO>}
     * @author WangYX
     * @date 2023/06/06 18:10
     */
    @DataScope(type = "2", value = @DataColumn(alias = "t1", name = "create_by_id", userid = "user_id"))
    List<WeMomentsTaskVO> list(WeMomentsTaskListRequest request);

}
