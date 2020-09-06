package com.study.spring.users.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.study.spring.users.dao.UsersDao;
import com.study.spring.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao usersDao;

	@Override
	public void addUser(UsersDto dto) {
		// TODO Auto-generated method stub
		String inputPwd = dto.getPwd();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd = encoder.encode(inputPwd);
		
		dto.setPwd(encodedPwd);
		
		usersDao.insert(dto);
	}

	@Override
	public Map<String, Object> checkid(String inputId) {
		// TODO Auto-generated method stub
		boolean isExist = usersDao.checkid(inputId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("isExist", isExist);
		
		return map;
	}

	@Override
	public void loginProcess(UsersDto dto, HttpServletRequest req, ModelAndView mView) {
		// TODO Auto-generated method stub
		
		UsersDto resultDto = usersDao.getData(dto.getId());
		
		boolean isValid = false;
		
		if(resultDto != null) {
			String encodedPwd = resultDto.getPwd();
			String inputPwd = dto.getPwd();
			
			isValid = BCrypt.checkpw(inputPwd, encodedPwd);
		}
		
		if(isValid) {
			req.getSession().setAttribute("id", dto.getId());
			mView.addObject("isSuccess", true);
		}
		
	}

	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();
	}

	@Override
	public void Infouser(ModelAndView mView, HttpSession session) {
		// TODO Auto-generated method stub
		
		String id = (String)session.getAttribute("id");
		
		UsersDto dto = usersDao.getData(id);
		
		mView.addObject("dto", dto);
	}
	
	
}
