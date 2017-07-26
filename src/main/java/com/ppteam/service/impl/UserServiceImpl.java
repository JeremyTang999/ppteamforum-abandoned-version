package com.ppteam.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppteam.dao.*;
import com.ppteam.entity.*;
import com.ppteam.entity.UserInfo;
import com.ppteam.json.*;
import com.ppteam.service.*;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserSecurityDao userSecurityDao;

	@Transactional
	@Override
	public boolean register(RegisterInfo info) {
		Calendar c=Calendar.getInstance();
		User u=new User(
				null,
				info.getUsername(),
				c.getTimeInMillis(),
				Role.IN_AUDIT);
		int id=userDao.add(u);
		
		userInfoDao.add(new UserInfo(
				id,
				Gender.stringToEnum(info.getGender()),
				null,
				null));
		
		System.out.println(info.getQuestion1());
		System.out.println(info.getAnswer1());
		
		Map<String,String> m=new HashMap<String,String>();
		m.put(info.getQuestion1(), info.getAnswer1());
		m.put(info.getQuestion2(), info.getAnswer2());
		m.put(info.getQuestion3(), info.getAnswer3());
		userSecurityDao.add(new UserSecurity(
				id, info.getPassword(), m));
		return true;
	}

	@Override
	public boolean usernameAvailable(String username) {
		return (userDao.getByUsername(username)==null)?true:false;
		
	}
	
	

}
