package com.dataart.conf;/**
 * Created by Evegeny on 10/05/2017.
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Component
public @interface RegistryUdf {
}
