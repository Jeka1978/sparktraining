package com.dataart.services;

import com.dataart.conf.CommonConfig;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.storage.StorageLevel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Evegeny on 28/05/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonConfig.class)
@ActiveProfiles("DEV")
public class LinkedInTest {
    @Autowired
    private SQLContext sqlContext;

    @Test
    public void linkedIn() throws Exception {
        DataFrame dataFrame = sqlContext.read().json("data/linkedIn/profiles.json");
        dataFrame.persist(StorageLevel.MEMORY_AND_DISK());
        dataFrame.show();

    }
}













