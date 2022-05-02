package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizeHttpsCalls {
    VisualizeHttpsCall[] value();
}
