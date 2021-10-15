package org.bootcamp.springboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@Getter @Setter
public class DbConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    @Profile("dev")
    public String testDatabaseConn() {
        System.out.println("DB conn for DEV - H2");
        System.out.println(this.driverClassName);
        System.out.println(this.url);
        return "DB conn to MYSQL_TEST - Test instance";
    }

    @Bean
    @Profile("prod")
    public String productionDatabaseConn() {
        System.out.println("DB conn for PROD - MySQL");
        System.out.println(this.driverClassName);
        System.out.println(this.url);
        return "DB conn to MYSQL_prod - Production instance";

    }
}
