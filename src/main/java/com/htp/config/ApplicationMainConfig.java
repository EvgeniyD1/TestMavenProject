package com.htp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.htp")
//@EnableLoadTimeWeaving
@EnableAspectJAutoProxy(proxyTargetClass = true) //create bean LogAspect that marked by annotation @Aspect
@Import({DatasourceConfiguration.class})
public class ApplicationMainConfig {

}