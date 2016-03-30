package com.hcb.gastronome.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by huchuanbin on 16/3/29.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLevel {
    String APPLICATION = "Application";
    String ACTIVITY = "Activity";
    String FRAGMENT = "Fragment";
    String SERVICE = "Service";

    String value() default APPLICATION;
}
