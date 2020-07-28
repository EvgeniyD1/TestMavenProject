package com.htp;

import com.htp.config.ApplicationBeanConfiguration;
import com.htp.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.htp"})
@Import({
        ApplicationBeanConfiguration.class,
        SwaggerConfig.class
})
public class SpringBootStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterApplication.class, args);
    }
}


