//package com.nmt.freelancermarketplacespringboot.core.configs;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatasourceConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .url(env.getProperty("spring.datasource.url"))
//                .username(env.getProperty("spring.datasource.username"))
//                .password(env.getProperty("spring.datasource.password"))
//                .build();
//    }
//}
