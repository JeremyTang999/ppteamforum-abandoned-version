package com.ppteam.dao;
import com.ppteam.entity.*;

public interface UserSecurityDao {
	public UserSecurity get(int id);
	public boolean add(UserSecurity us);
	public boolean update(UserSecurity us);
	public boolean delete(int id);


}
