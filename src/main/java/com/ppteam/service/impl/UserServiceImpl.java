package com.ppteam.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppteam.dao.*;
import com.ppteam.entity.*;
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
	public boolean register(RegisterInfo rinfo) {
		Calendar c=Calendar.getInstance();
		User u=new User(
				null,
				rinfo.getUsername(),
				c.getTimeInMillis(),
				Role.IN_AUDIT);
		int id=userDao.add(u);
		
		userInfoDao.add(
				new com.ppteam.entity.UserInfo(
						id,
						Gender.fromString(rinfo.getGender()),
						null,
						null
				)
		);
		
		List<QuestionAndAnswer> l=new ArrayList<QuestionAndAnswer>();
		l.add(new QuestionAndAnswer(rinfo.getQuestion1(),rinfo.getAnswer1()));
		l.add(new QuestionAndAnswer(rinfo.getQuestion2(),rinfo.getAnswer2()));
		l.add(new QuestionAndAnswer(rinfo.getQuestion3(),rinfo.getAnswer3()));
		
		
		userSecurityDao.add(new UserSecurity(
				id, rinfo.getPassword(),l));
		return true;
	}

	@Override
	public boolean usernameAvailable(String username) {
		return (userDao.getByUsername(username)==null)?true:false;
		
	}

	@Override
	public com.ppteam.json.UserInfo getUserInfo(int id) {
		// TODO Auto-generated method stub
		com.ppteam.entity.UserInfo einfo=userInfoDao.get(id);
		if (einfo==null)
			return null;
		
		com.ppteam.json.UserInfo jinfo=
				new com.ppteam.json.UserInfo();
		jinfo.setGender(einfo.getGender().toString());
		jinfo.setPersonalSignature(einfo.getPersonalSignature());
		return jinfo;
	}

	@Override
	public boolean setUserInfo(com.ppteam.json.UserInfo jinfo) {
		com.ppteam.entity.UserInfo einfo=userInfoDao.get(jinfo.getId());
		if((jinfo.getGender()!=null) && !(jinfo.getGender().equals(""))){
			einfo.setGender(Gender.fromString(jinfo.getGender()));
		}
		if((jinfo.getPhotoPath()!=null)&& !(jinfo.getPhotoPath().equals(""))){
			einfo.setPhotoPath(jinfo.getPhotoPath());
		}
		if((jinfo.getPersonalSignature()!=null)&& !(jinfo.getPersonalSignature().equals(""))){
			einfo.setPersonalSignature(jinfo.getPersonalSignature());
		}
		userInfoDao.update(einfo);
		return false;
	}
	
	
	
	

}
