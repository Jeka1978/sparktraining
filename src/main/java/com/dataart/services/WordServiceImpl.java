package com.dataart.services;

import com.dataart.conf.AutowiredBroadcast;
import com.dataart.conf.UserProperties;
import com.dataart.utils.WordsUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.List;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Service
public class WordServiceImpl implements WordService {


    @AutowiredBroadcast
    private Broadcast<UserProperties> userPropertiesBroadcast;

    @Override
    public List<String> topX(JavaRDD<String> lines, int x) {
        return lines.map(String::toLowerCase)
                .flatMap(WordsUtil::getWords)
                .filter(word->!this.userPropertiesBroadcast.value().getGarbage().contains(word))
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .map(Tuple2::_2)
                .take(x);
    }
}











