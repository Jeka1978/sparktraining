package com.dataart.conf;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Configuration
@ComponentScan
@PropertySource("classpath:user.properties")
@ComponentScan(basePackages = "com.dataart.services")
public class CommonConfig {

    @Autowired
    private SparkConf sparkConf;
    @Bean
    public JavaSparkContext sc(){
        return new JavaSparkContext(sparkConf);
    }

    @Bean
    public SQLContext sqlContext(){
        return new SQLContext(sc());
    }

    @Bean
    public SparkSession sparkSession(){
        return new SparkSession(sc().sc());
    }
}



















