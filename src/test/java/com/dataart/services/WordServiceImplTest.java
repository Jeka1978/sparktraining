package com.dataart.services;

import com.dataart.conf.CommonConfig;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Evegeny on 28/05/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonConfig.class)
@ActiveProfiles("DEV")
public class WordServiceImplTest {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private WordService wordService;

    @Test
    public void topX() throws Exception {
        JavaRDD<String> rdd = sc.parallelize(Arrays.asList("java is the best", "java 9 not so good", "java java java"));
        List<String> strings = wordService.topX(rdd, 1);
        Assert.assertEquals("java",strings.get(0));
    }

    @Test
    public void testThatYesterdayIsTheMostPopularWordOfBeatles() throws Exception {
        JavaRDD<String> rdd = sc.textFile("data/songs/beatles/*.txt");
        List<String> topX = wordService.topX(rdd, 1);
        Assert.assertEquals("yesterday",topX.get(0));

    }
}













