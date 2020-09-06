package com.study.spring.users.dao;

import com.study.spring.users.dto.UsersDto;

public interface UsersDao {
	public void insert(UsersDto dto);
	public boolean checkid(String inputId);
	public boolean isValid(UsersDto dto);
	public UsersDto getData(String id);
}
