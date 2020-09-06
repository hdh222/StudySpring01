package com.study.spring.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.spring.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{

	@Autowired
	private SqlSession session;

	@Override
	public void insert(UsersDto dto) {
		// TODO Auto-generated method stub
		session.insert("users.insert", dto);
	}

	@Override
	public boolean checkid(String inputId) {
		// TODO Auto-generated method stub
		String id = session.selectOne("users.checkid", inputId);
		if(id == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean isValid(UsersDto dto) {
		// TODO Auto-generated method stub
		
		String id = session.selectOne("users.isValid", dto);
		
		if(id == null) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public UsersDto getData(String id) {
		// TODO Auto-generated method stub
		return session.selectOne("users.getData",id);
	}
	
	
}
