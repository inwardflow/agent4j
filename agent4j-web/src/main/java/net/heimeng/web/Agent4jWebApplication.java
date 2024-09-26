package net.heimeng.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot 启动类
 * @author InwardFlow
 */
@SpringBootApplication
@ConfigurationPropertiesScan("net.heimeng.**.config")
@MapperScan("net.heimeng.*.mapper")
@ComponentScan("net.heimeng.**")
public class Agent4jWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(Agent4jWebApplication.class, args);
    }

}
