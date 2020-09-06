package com.study.spring.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.study.spring.users.dto.UsersDto;
import com.study.spring.users.service.UsersService;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/users/signup-form")
	public String signupForm() {
		
		
		return "users/signup-form";
	}
	
	@RequestMapping(value = "/users/checkid", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkid(String inputId) {
		
		return usersService.checkid(inputId);
	}
	
	@RequestMapping(value = "/users/signup", method = RequestMethod.POST)
	public ModelAndView insert(ModelAndView mView, UsersDto dto) {
		
		usersService.addUser(dto);
		
		mView.setViewName("users/signup");
		return mView;
	}
	
	@RequestMapping(value = "/users/login-form")
	public String loginForm(HttpServletRequest req) {
		String url = req.getParameter("url");
		if(url == null) {
			url = req.getContextPath() + "/home.do";
		}
		
		req.setAttribute("url", url);

		return "users/login-form";
		
	}
	
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public ModelAndView login(UsersDto dto, HttpServletRequest req, ModelAndView mView) {
		
		String url=req.getParameter("url");
		String encodedUrl=URLEncoder.encode(url);
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		
		usersService.loginProcess(dto, req, mView);
		
		mView.setViewName("users/login");
		return mView;
	}

	@RequestMapping(value = "/users/private/logout")
	public String logout(HttpSession session) {
		
		usersService.logout(session);
		return "redirect:/home.do";
	}
	
	@RequestMapping(value = "/users/private/info")
	public ModelAndView UserInfo(HttpSession session, ModelAndView mView) {
		
		usersService.Infouser(mView, session);
		mView.setViewName("users/private/info");
		return mView;
	}
}
