package com.hcb.gastronome.di.scope;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.inject.Scope;

/**
 * Created by huchuanbin on 16/3/29.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
