package com.ppteam.service;

import com.ppteam.json.*;

public interface UserService {
	
	public boolean register(RegisterInfo info);
	public boolean usernameAvailable(String username);

}
