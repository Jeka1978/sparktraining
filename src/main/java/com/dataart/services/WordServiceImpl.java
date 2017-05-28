package com.dataart.services;

import org.apache.spark.api.java.JavaRDD;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Service
public class WordServiceImpl implements WordService {
    @Override
    public List<String> topX(JavaRDD<String> lines, int x) {
        return null;
    }
}
