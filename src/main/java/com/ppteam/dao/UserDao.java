package com.ppteam.dao;

import com.ppteam.entity.*;

public interface UserDao extends BaseDao<User>{
	//CURD
	/* inherit from BaseDao继承
	 * public User get(int id);
	public Integer add(User u);
	public boolean update(User u);
	public boolean delete(int id);*/

	//其他方法
	public User getByUsername(String username);
	//public boolean addAllUserInfo(User u,UserInfo ui,UserSecurity us);

}
