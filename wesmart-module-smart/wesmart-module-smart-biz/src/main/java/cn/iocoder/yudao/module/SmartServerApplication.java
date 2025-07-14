package cn.iocoder.yudao.module;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目的启动类
 * <p>
 * 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
 * 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
 * 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
 *
 * @author 芋道源码
 */
@MapperScan("cn.iocoder.yudao.module.**.mapper")
@ComponentScan(basePackages = {
        "cn.iocoder.yudao.module",  // 扫描业务模块
        "cn.iocoder.yudao.framework" // 扫描框架模块
})
//@ForestScan("cn.iocoder.yudao.module.**")
@ForestScan(basePackages = {
        "cn.iocoder.yudao.module.wecom" // 明确指定扫描包
})
@SpringBootApplication
public class SmartServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartServerApplication.class, args);
    }

}
