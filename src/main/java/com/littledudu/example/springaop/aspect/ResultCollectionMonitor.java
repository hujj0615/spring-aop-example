package com.littledudu.example.springaop.aspect;

import java.util.Arrays;
import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

import com.littledudu.example.springaop.annotation.ResultCollection;

/**
 * @author hujinjun
 * @date 2015-9-22 
 */
@Aspect
public class ResultCollectionMonitor implements Ordered {
	
	@Around(value="execution(public * *(..)) && target(bean) && @annotation(resultCollection)", argNames="bean,resultCollection")
	public Object monitor(ProceedingJoinPoint jp, Object bean, ResultCollection resultCollection) throws Throwable {
		Signature signature = jp.getSignature();
		String methodName = signature.getName();
		String clazz = jp.getTarget().getClass().getSimpleName();
		
		final String key = clazz + "." + methodName;
		
		System.out.println((String.format("calling %s with args %s", key, Arrays.toString(jp.getArgs()))));
		
		int min = resultCollection.min();
		int max = resultCollection.max();
		
		int size = 0;
		try{
			Object obj = jp.proceed(); // continue on the intercepted method
			if(obj == null) {
				System.out.println(String.format("return null calling %s with args %s", key, Arrays.toString(jp.getArgs())));
			} else if(obj instanceof Collection) {
				Collection<?> c = (Collection.class.cast(obj));
				size = c.size();
				
				
				System.out.println(String.format("method return size: %s, %d", key, size));
				
				if(size < min) {
					System.out.println(String.format("return less than min, calling %s with args %s: %d", key, Arrays.toString(jp.getArgs()), size));
				} else if(size > max) {
					System.out.println(String.format("return more than max, calling %s with args %s: %d", key, Arrays.toString(jp.getArgs()), size));
				}
			} else {
				throw new RuntimeException("method " + key + " not suitable for annotation ReturnCollection");
			}
			return obj;
		} catch(Throwable t) {
			throw t;
		} finally {
		}
	}
	
	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE + 1;
	}
}
