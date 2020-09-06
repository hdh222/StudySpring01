package com.study.spring.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.study.spring.users.dto.UsersDto;

public interface UsersService {
	public void addUser(UsersDto dto);
	public Map<String, Object> checkid(String inputId);
	public void loginProcess(UsersDto dto, HttpServletRequest req, ModelAndView mView);
	public void logout(HttpSession session);
	public void Infouser(ModelAndView mView, HttpSession session);
}
