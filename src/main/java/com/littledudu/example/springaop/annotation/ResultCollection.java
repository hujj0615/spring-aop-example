/**
 * 
 */
package com.littledudu.example.springaop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hujinjun
 * @date 2015-09-21 
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultCollection {
	int min() default 0;
	int max() default Integer.MAX_VALUE;
}
