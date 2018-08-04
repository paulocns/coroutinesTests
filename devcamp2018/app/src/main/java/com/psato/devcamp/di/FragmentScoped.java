package com.psato.devcamp.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by athila on 31/05/16.
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {
}
