package com.dataart.conf;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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
}
























