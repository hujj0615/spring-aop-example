package com.littledudu.example.springaop.service.impl;

import org.springframework.stereotype.Component;

import com.littledudu.example.springaop.service.EchoService;
import com.littledudu.example.springaop.annotation.PerformanceTracking;

/**
 * @author hujinjun
 * @date 2015-9-22 
 */
@Component
public class EchoServiceImpl implements EchoService {

	@Override
	@PerformanceTracking
	public String echo(String msg) {
		System.out.println("in echo()");
		try {
			Thread.sleep(1500L);
		} catch (InterruptedException e) {
		}
		return msg;
	}

}
