package com.ppteam.service;

import com.ppteam.json.*;

public interface UserService {
	
	public boolean register(RegisterInfo info);
	public boolean usernameAvailable(String username);
	public UserInfo getUserInfo(int id);
	public boolean setUserInfo(UserInfo ui);
	public BasicInfo getBasicInfo(String username);
	public boolean checkAndModifySecurity(SecurityInfo info);
	public SecurityInfo getQuestions(int id);
}
