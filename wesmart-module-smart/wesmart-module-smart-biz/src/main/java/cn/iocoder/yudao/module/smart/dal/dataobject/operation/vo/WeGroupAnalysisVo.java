package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客群分析
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeGroupAnalysisVo {

    @Schema(description = "客群总数")
    private int totalCnt;

    @Schema(description = "今日新增客群数")
    private int tdGroupAddCnt;

    @Schema(description = "昨日新增客群数")
    private int ydGroupAddCnt;

    @Schema(description = "今日客群解散数")
    private int tdGroupDissolveCnt;

    @Schema(description = "昨客群解散数")
    private int ydGroupDissolveCnt;

    @Schema(description = "客群成员总数")
    private int memberTotalCnt;

    @Schema(description = "含员工数")
    private int memberUserCnt;

    @Schema(description = "今日新增客群成员")
    private int tdMemberAddCnt;

    @Schema(description = "昨日新增客群成员")
    private int ydMemberAddCnt;

    @Schema(description = "今日流失客群成员")
    private int tdMemberQuitCnt;

    @Schema(description = "昨日流失客群成员")
    private int ydMemberQuitCnt;

    public int getYdGroupAddCnt() {
        return this.tdGroupAddCnt - ydGroupAddCnt;
    }

    public int getYdGroupDissolveCnt() {
        return this.tdGroupDissolveCnt - ydGroupDissolveCnt;
    }

    public int getYdMemberAddCnt() {
        return this.tdMemberAddCnt - ydMemberAddCnt;
    }

    public int getYdMemberQuitCnt() {
        return this.tdMemberQuitCnt - ydMemberQuitCnt;
    }
}
