package com.dataart.conf;

import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static org.apache.spark.sql.functions.*;

/**
 * Created by Evegeny on 08/05/2017.
 */
@Service
public class CountryService {
    public static final String COUNTRY_CODE = "item_site_id";
    public static final String PRICE = "start_price_lstg_curncy";
    private static final String TOTAL_PRICE = "total_price";
    private static final String COUNTRY_NAME = "country_name";
    @Autowired
    private SQLContext sparkSession;

    @AutowiredBroadcast
    private Broadcast<UserProperties> userProperties;



    @Value("${pathToRead}")
    private String pathToRead;

    public void start(){
        DataFrame dataset = sparkSession.read().json(pathToRead).select(COUNTRY_CODE, PRICE);
        dataset.show();
        dataset = dataset.groupBy(COUNTRY_CODE).agg(round(sum(col(PRICE)),2).as(TOTAL_PRICE)).orderBy(col(TOTAL_PRICE).desc());
        dataset =  dataset.withColumn(COUNTRY_NAME, callUDF(CountryNameByCodeResolver.class.getName(),col(COUNTRY_CODE)));
        dataset.show();


    }




}















