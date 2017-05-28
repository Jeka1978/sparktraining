package com.dataart.conf;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Configuration
@Profile("DEV")
public class DevConf {
    @Bean
    public SparkConf sparkConf(){
        return new SparkConf().setAppName("Taxi_DEV").setMaster("local[*]");
        /*
         SparkConf conf = new SparkConf();
        conf.setAppName("mbi etl");
        conf.set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        conf.set("spark.kryo.registrationRequired","false");

        Reflections com = new Reflections("my data packages");
        Set<Class<? extends Serializable>> classes = com.getSubTypesOf(Serializable.class);
        Class[] starhomeSerializableClasses = classes.toArray(new Class[classes.size()]);
        conf.registerKryoClasses(new Class[]{Object[].class, HashMap.class, ArrayList.class,LinkedHashSet.class,TorrentBroadcast.class,BroadcastBlockId.class});
        conf.registerKryoClasses(starhomeSerializableClasses);
        return conf;
         */
    }
}
