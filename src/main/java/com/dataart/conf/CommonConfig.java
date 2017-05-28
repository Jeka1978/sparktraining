package com.dataart.conf;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Configuration
@ComponentScan
@ComponentScan(basePackages = "com.dataart.services")
public class CommonConfig {

    @Autowired
    private SparkConf sparkConf;
    @Bean
    public JavaSparkContext javaSparkContext(){
        return new JavaSparkContext(sparkConf);
    }
}
