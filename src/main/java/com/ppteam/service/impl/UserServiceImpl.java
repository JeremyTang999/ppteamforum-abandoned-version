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
		System.out.println(jinfo.getId());
		com.ppteam.entity.UserInfo einfo=userInfoDao.get(jinfo.getId());
		//if((jinfo.getGender()!=null) && !(jinfo.getGender().equals(""))){
			einfo.setGender(Gender.fromString(jinfo.getGender()));
		//}
		//if((jinfo.getPhotoPath()!=null)&& !(jinfo.getPhotoPath().equals(""))){
			einfo.setPhotoPath(jinfo.getPhotoPath());
		//}
		//if((jinfo.getPersonalSignature()!=null)&& !(jinfo.getPersonalSignature().equals(""))){
			einfo.setPersonalSignature(jinfo.getPersonalSignature());
		//}
		System.out.println(einfo==null);
		userInfoDao.update(einfo);
		return true;
	}

	
	

	@Override
	public BasicInfo getBasicInfo(String username) {
		if(username==null) 
			return null;
		User u=userDao.getByUsername(username);
		BasicInfo bi=new BasicInfo();
		bi.setId(u.getId());
		bi.setUsername(u.getUsername());
		bi.setRole(u.getRole().toString());
		return bi;
	}

	@Override
	public boolean checkAndModifySecurity(SecurityInfo info) {
		UserSecurity us=userSecurityDao.get(info.getId());
		if(checkQuestions(us.getQuestionsAndAnswers(), info.getOriQA())){
			return false;
		}
		if(!info.getOriPass().equals(us.getPassword())){
			return false;
		}
		
		if(info.getNewPass()==null || info.getNewPass().equals("")){
			
		}
		else{
			us.setPassword(info.getNewPass());
		}
		
		if(info.getNewQA()==null || info.getNewQA().isEmpty()){
			
		}
		else{
			us.setQuestionsAndAnswers(info.getNewQA());
		}
		
		return true;
	}
	
	
	
	@Override
	public SecurityInfo getQuestions(int id) {
		UserSecurity us=userSecurityDao.get(id);
		SecurityInfo si=new SecurityInfo();
		List<QuestionAndAnswer> l=us.getQuestionsAndAnswers();
		for(QuestionAndAnswer qa:l){
			qa.setAnswer(null);
		}
		si.setId(id);
		si.setOriQA(l);
		return si;
		
	}

	private boolean checkQuestions(List<QuestionAndAnswer> oriQA,List<QuestionAndAnswer> newQA){
		for(QuestionAndAnswer oqa:oriQA){
			for(QuestionAndAnswer nqa:newQA){
				if(nqa.getQuestion().equals(oqa.getAnswer())){
					if(!nqa.getAnswer().equals(oqa.getAnswer())){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
}
