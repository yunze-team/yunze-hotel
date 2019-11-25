package com.dotw.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author lazyb
 * @create 2019/11/25
 * @desc
 **/
@SpringBootApplication
@ComponentScan("com.dotw")
@ServletComponentScan
@EnableJpaRepositories("com.dotw.core.repository")
@EntityScan("com.dotw.core.domain")
@EnableJpaAuditing
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
