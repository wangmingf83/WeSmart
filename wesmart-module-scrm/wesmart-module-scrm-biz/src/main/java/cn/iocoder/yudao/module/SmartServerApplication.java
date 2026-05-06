package cn.iocoder.yudao.module;

import cn.iocoder.yudao.framework.mybatis.config.YudaoMybatisAutoConfiguration;
import cn.iocoder.yudao.module.smart.config.SmartWeChatConfig;
import com.dtflys.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

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
        "cn.iocoder.yudao.**",  // 扫描业务模块
        "cn.iocoder.yudao.framework" // 扫描框架模块
})
//@ForestScan("cn.iocoder.yudao.module.**")
@ForestScan(basePackages = {
//        "cn.iocoder.yudao.**" // 明确指定扫描包
        "cn.iocoder.yudao.module.wecom.**" // 明确指定扫描包
})
@SpringBootApplication(exclude = {YudaoMybatisAutoConfiguration.class})
//@SpringBootApplication
public class SmartServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SmartServerApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(SmartServerApplication.class, args);

        // 检查 smartChatConfig 是否成功注册
        SmartWeChatConfig config = context.getBean(SmartWeChatConfig.class);
        System.out.println("smartChatConfig loaded: " + (config != null));

        // 打印所有 ConfigurationProperties Bean
        String[] configBeans = context.getBeanNamesForType(SmartWeChatConfig.class);
        System.out.println("smartChatConfig beans: " + Arrays.toString(configBeans));

        // 检查 WeAccessTokenInterceptor（子类）
//        WeAccessTokenInterceptor interceptor = context.getBean(WeAccessTokenInterceptor.class);
//        System.out.println("Interceptor instance: " + interceptor);
    }

}
