package com.xxxx.springboot;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Starter extends SpringBootServletInitializer {

     private static org.slf4j.Logger logger = LoggerFactory.getLogger(Starter.class);
     public static void main(String[] args) {
         logger.info("spring boot应用开始启动");
         SpringApplication.run(Starter.class,args); }

         //重写configure ⽅法来实现，在部署项⽬时指定外部 Tomcat 读取项⽬⼊⼝⽅法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Starter.class);
    }
}
