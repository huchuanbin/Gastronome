package com.hcb.gastronome.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by huchuanbin on 16/3/29.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceScope {
}
