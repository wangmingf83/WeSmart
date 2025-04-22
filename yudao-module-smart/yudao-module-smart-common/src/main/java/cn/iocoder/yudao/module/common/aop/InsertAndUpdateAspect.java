//package cn.iocoder.yudao.module.common.aop;
//
//
//import cn.iocoder.yudao.module.common.core.domain.BaseEntity;
//import cn.iocoder.yudao.module.common.exception.CustomException;
//import cn.iocoder.yudao.module.common.utils.SecurityUtils;
//import cn.iocoder.yudao.module.common.utils.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Collection;
//import java.util.Date;
//
///**
// * @author danmo
// */
//@Aspect
//@Configuration
//public class InsertAndUpdateAspect {
//
//
//    @Pointcut("execution( * com.GuituAi.*.mapper.*.*Mapper.insert*(..)))")
//    public void executeInsert() {
//    }
//
//    @Pointcut("execution( * com.GuituAi.*.mapper.*.*Mapper.update*(..)))")
//    public void executeUpdate() {
//    }
//
//
//    @Before(value = "executeInsert()")
//    public void insertHandle(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0L) {
//            for (int i = 0; i < args.length; i++) {
//                Object arg = args[i];
//                if (arg instanceof Collection) {
//                    Collection collection = (Collection) arg;
//                    for (Object next : collection) {
//                        isInsertBaseEntity(next);
//                    }
//                } else if (arg instanceof BaseEntity) {
//                    isInsertBaseEntity(arg);
//                }
//            }
//        }
//    }
//
//    private void isInsertBaseEntity(Object arg) {
//        if (arg instanceof BaseEntity) {
//            BaseEntity next1 = (BaseEntity) arg;
//            String userName = getUserName();
//            if(StringUtils.isEmpty(next1.getCreateBy())){
//                next1.setCreateBy(userName);
//            }
//            if(StringUtils.isEmpty(next1.getUpdateBy())){
//                next1.setUpdateBy(userName);
//            }
//
//            Date date = new Date();
//            if (next1.getCreateTime() == null) {
//                next1.setCreateTime(date);
//            }
//            if (next1.getUpdateTime() == null) {
//                next1.setUpdateTime(date);
//            }
//        }
//    }
//
//    private String getUserName() {
//        String userName;
//        try {
//            userName = SecurityUtils.getUserName();
//        } catch (CustomException e){
//            userName = "admin";
//        }
//        return userName;
//    }
//
//    @Before(value = "executeUpdate()")
//    public void updateHandle(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0L) {
//            for (int i = 0; i < args.length; i++) {
//                Object arg = args[i];
//                if (arg instanceof Collection) {
//                    Collection collection = (Collection) arg;
//                    for (Object next : collection) {
//                        if (next instanceof BaseEntity) {
//                            BaseEntity next1 = (BaseEntity) next;
//                            if(StringUtils.isEmpty(next1.getUpdateBy())){
//                                next1.setUpdateBy(getUserName());
//                            }
//
//                            Date date = new Date();
//                            if (next1.getUpdateTime() == null) {
//                                next1.setUpdateTime(date);
//                            }
//                        }
//                    }
//                } else if (arg instanceof BaseEntity) {
//                    BaseEntity arg1 = (BaseEntity) arg;
//                    if(StringUtils.isEmpty(arg1.getUpdateBy())){
//                        arg1.setUpdateBy(getUserName());
//                    }
//
//                    Date date = new Date();
//                    if (arg1.getUpdateTime() == null) {
//                        arg1.setUpdateTime(date);
//                    }
//                }
//            }
//        }
//    }
//}
