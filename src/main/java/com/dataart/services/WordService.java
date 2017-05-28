package com.dataart.services;

import org.apache.spark.api.java.JavaRDD;

import java.util.List;

/**
 * Created by Evegeny on 28/05/2017.
 */
public interface WordService {
    List<String> topX(JavaRDD<String> lines, int x);
}
