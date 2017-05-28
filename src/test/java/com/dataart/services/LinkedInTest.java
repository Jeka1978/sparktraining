package com.dataart.services;

import com.dataart.conf.CommonConfig;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.storage.StorageLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.apache.spark.sql.functions.*;

/**
 * Created by Evegeny on 28/05/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonConfig.class)
@ActiveProfiles("DEV")
public class LinkedInTest {
    private static final String SALARY = "salary";
    private static final String AGE = "age";
    private static final String KEYWORDS = "keywords";
    public static final String KEYWORD = "keyword";
    public static final String AMOUNT = "amount";
    @Autowired
    private SQLContext sqlContext;

    @Test
    public void linkedIn() throws Exception {
        Dataset<Row> dataFrame = sqlContext.read().json("data/linkedIn/profiles.json");
        dataFrame.persist(StorageLevel.MEMORY_AND_DISK());
        dataFrame.show();
        dataFrame.printSchema();
        Arrays.stream(dataFrame.schema().fields()).forEach(StructField::dataType);
        dataFrame = dataFrame.withColumn(SALARY, col(AGE).multiply(10).multiply(size(col(KEYWORDS))));
        dataFrame.persist(StorageLevel.MEMORY_AND_DISK());
        dataFrame.show();

        Dataset<Row> sortedPopularTechnologies = dataFrame.withColumn(KEYWORD, explode(col(KEYWORDS))).select(KEYWORD)
                .groupBy(KEYWORD).agg(count(KEYWORD).as(AMOUNT)).orderBy(col(AMOUNT).desc());
        sortedPopularTechnologies.show();

        Row row = sortedPopularTechnologies.head();
        String mostPopular = row.getAs(KEYWORD);
        System.out.println("mostPopular = " + mostPopular);

        dataFrame.filter(col(SALARY).leq(1200).and(array_contains(col(KEYWORDS),mostPopular))).show();



    }
}













