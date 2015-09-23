package com.littledudu.example.springaop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.littledudu.example.springaop.service.EchoService;

/**
 * @author hujinjun
 * @date 2015-9-22 
 */
@Controller
public class EchoController {
	
	@Autowired
	private EchoService echoService;
	
	@ResponseBody
	@RequestMapping(value = "echo.s", method = RequestMethod.GET)
	public String echo(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("msg") String msg) {
		return echoService.echo(msg);
	}
}
