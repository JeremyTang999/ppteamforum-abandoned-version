package com.ppteam.dao;
import com.ppteam.entity.*;

public interface UserInfoDao {
	public UserInfo get(int id);
	public boolean add(UserInfo ui);
	public boolean update(UserInfo ui);
	public boolean delete(int id);

}
