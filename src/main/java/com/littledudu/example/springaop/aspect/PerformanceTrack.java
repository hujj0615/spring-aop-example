package com.littledudu.example.springaop.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

/**
 * @author hujinjun
 * @date 2015-9-22 
 */
@Aspect
public class PerformanceTrack implements Ordered {

	private long methodWarningThreshold = 1000L;
	
	@Around("@annotation(com.littledudu.example.springaop.annotation.PerformanceTracking)")
	public Object monitor(ProceedingJoinPoint jp) throws Throwable {
		Signature signature = jp.getSignature();
		String methodName = signature.getName();
		String clazz = jp.getTarget().getClass().getSimpleName();
		
		final String key = clazz + "." + methodName;
		
		long st = System.currentTimeMillis();
		long cost = 0L;
		//System.out.println((String.format("calling %s with args %s", key, Arrays.toString(jp.getArgs()))));
		try{
			Object obj = jp.proceed(); // continue on the intercepted method
			
			cost = System.currentTimeMillis() - st;
			
			if(cost > methodWarningThreshold) {
				System.out.println((String.format("calling %s exceed warning threshold %s, %d", key, Arrays.toString(jp.getArgs()), cost)));
			}
			
			return obj;
		} catch(Throwable t) {
			cost = System.currentTimeMillis() - st;
			throw t;
		} finally {
		}
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	} 
}
