//package cn.iocoder.yudao.module.smart.config.mybatis;
//
//import cn.iocoder.yudao.module.common.annotation.DataScope;
//import cn.iocoder.yudao.module.common.core.domain.entity.SysUser;
//import cn.iocoder.yudao.module.common.utils.DataScopeSqlUtils;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.Parenthesis;
//import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
//import net.sf.jsqlparser.statement.select.PlainSelect;
//
///**
// * @author danmo
// * @description
// * @date 2022/5/11 11:40
// **/
//public class PearlDataScopeHandler implements DataScopeHandler {
//
//
//
//
//    @Override
//    public String setWhereForSysUser(DataScope dataScope, SysUser sysUser){
//
//
//        return DataScopeSqlUtils.setWhereForSysUser(dataScope,sysUser);
//
//    }
//
//    @Override
//    public String setWhereForDept(DataScope dataScope, SysUser sysUser) {
//
//
//        return DataScopeSqlUtils.setWhereForDept(dataScope, sysUser);
//    }
//
//    @Override
//    public String setWhereForDeptAndChild(DataScope dataScope, SysUser sysUser) {
//
//        return DataScopeSqlUtils.setWhereForDeptAndChild(dataScope, sysUser);
//    }
//
//    @Override
//    public String setWhereForSelf(DataScope dataScope, SysUser sysUser) {
//
//        return DataScopeSqlUtils.setWhereForSelf(dataScope, sysUser);
//    }
//
//    public void setWhere(PlainSelect plainSelect, Expression expression) {
//        Expression where = plainSelect.getWhere();
//        if (where == null) {
//            // 不存在 where 条件
//            plainSelect.setWhere(new Parenthesis(expression));
//        } else {
//            // 存在 where 条件 and 处理
//            plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), expression));
//        }
//    }
//}
