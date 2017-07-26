package com.ppteam.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ppteam.dao.UserDao;
import com.ppteam.entity.Role;
import com.ppteam.entity.User;
import com.ppteam.entity.UserInfo;
import com.ppteam.entity.UserSecurity;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User get(int id) {
		List<User> u=jdbcTemplate.query("select * from user where id=?",new Object[]{id},
			new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User u=new User(
							rs.getInt("id"),
							rs.getString("username"),
							rs.getLong("register_time"),
							Role.stringToEnum(rs.getString("role")));
					return u;
				}
			});
		if(u.size()==0) return null;
		return u.get(0);
	}

	@Override
	public Integer add(User u) {
		Object[] params={u.getUsername(),u.getRegisterTime()
				,Role.enumToString(u.getRole())};
		jdbcTemplate.update("insert into user values(null,?,?,?)"
				,params);
		List<Integer> l=jdbcTemplate.query("select last_insert_id()"
				,new RowMapper<Integer>(){

					@Override
					public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						return rs.getInt("last_insert_id()");
					}
					
				});
		if(l.size()!=1)
			return null;
		else
			return l.get(0);
	}

	@Override
	public boolean update(User u) {
		String sql="update user set "+
				"username=?,register_time=?,role=? "+
				"where id=?";
		Object params=new Object[]{
				u.getUsername(),
				u.getRegisterTime(),
				u.getRole(),
				u.getId()};
		jdbcTemplate.update(sql,params);
		return true;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getByUsername(String username) {
		List<User> u=jdbcTemplate.query("select * from user where username=?",new Object[]{username},
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User u=new User(
								rs.getInt("id"),
								rs.getString("username"),
								rs.getLong("register_time"),
								Role.stringToEnum(rs.getString("role")));
						return u;
					}
				});
			if(u.size()!=1) return null;
			return u.get(0);
	}

	
	

}
