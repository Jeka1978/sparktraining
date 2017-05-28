package com.dataart.conf;

import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Component
public class UdfRegistrarApplicationListener {

    @Autowired
    private SQLContext sqlContext;

    private Map<Class, DataType> types = new HashMap<>();

    public UdfRegistrarApplicationListener() {
        types.put(String.class, DataTypes.StringType);
        types.put(Integer.class, DataTypes.IntegerType);
        types.put(Boolean.class, DataTypes.BooleanType);
        types.put(Double.class, DataTypes.DoubleType);

    }

    @EventListener   /// since spring 4.3
    public void registerUdf1(ContextRefreshedEvent event) {
        Collection<UDF1> udf1s = event.getApplicationContext().getBeansOfType(UDF1.class).values();
        for (UDF1 udf1 : udf1s) {
            Type type = ((ParameterizedType) udf1.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
            DataType dataType = types.get(type);
            sqlContext.udf().register(udf1.getClass().getName(), udf1, dataType);
        }
    }
}
