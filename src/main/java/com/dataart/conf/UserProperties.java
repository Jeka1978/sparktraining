package com.dataart.conf;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Component
public class UserProperties implements Serializable {
    @Getter
    private List<String> garbage;

    @Value("${garbage}")
    public void setGarbage(String[] garbage) {
        this.garbage = Arrays.asList(garbage);
    }

    @Getter
    private Map<Long, String> countryMap = new HashMap<>();

    @Value("${countries}")
    private void buildCountryMap(String line) {
        String[] arr = line.split(";");
        for (String pair : arr) {
            String[] split = pair.split("=");
            if(split.length!=2) throw new IllegalStateException("you are an idiot");
            countryMap.put(Long.valueOf(split[0]), split[1]);
        }
    }
}
























