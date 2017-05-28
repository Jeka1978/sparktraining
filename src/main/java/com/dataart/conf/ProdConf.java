package com.dataart.conf;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Configuration
@Profile("PROD")
public class ProdConf {
    @Bean
    public SparkConf sparkConf(){
        return new SparkConf().setAppName("Taxi_PROD");
    }
}
