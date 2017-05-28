package com.dataart.conf;

import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;

/**
 * Created by Evegeny on 10/05/2017.
 */
@RegistryUdf
public class CountryNameByCodeResolver implements UDF1<Long,String> {

    @AutowiredBroadcast
    private Broadcast<UserProperties> userProperties;



    @Override
    public String call(Long countryCode) throws Exception {
        return userProperties.value().getCountryMap().get(countryCode);
    }
}
