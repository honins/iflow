package com.wolwo.iflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @date 2021/1/22
 * @author hy
 */
@SpringBootApplication
@MapperScan({"com.wolwo.iflow.infra.mapper"})
@EnableSwagger2
public class IflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(IflowApplication.class, args);
    }

}
