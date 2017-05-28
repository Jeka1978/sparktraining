package com.dataart.conf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Evegeny on 28/05/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonConfig.class)
@ActiveProfiles("DEV")
public class CountryServiceTest {
    @Autowired
    private CountryService countryService;

    @Test
    public void start() throws Exception {
        countryService.start();
    }

}