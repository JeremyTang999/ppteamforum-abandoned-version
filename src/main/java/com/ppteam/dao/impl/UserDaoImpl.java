package com.ppteam.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
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
		String sql="select * from user where id=?";
		Object[] queryObjects={id};
		
		List<User> u=jdbcTemplate.query(sql,queryObjects,
			new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) 
						throws SQLException {
					User u=new User();
					u.setId(rs.getInt("id"));
					u.setUsername(rs.getString("username"));
					u.setRegisterTime(rs.getLong("register_time"));
					u.setRole(Role.fromString(rs.getString("role")));
					return u;
				}
			});
		//通常查询得到一个或没有结果
		if(u==null || u.size()!=1) 
			return null;
		else 
			return u.get(0);
	}

	@Override
	public Integer add(User u) {
		String sql="insert into user values(null,?,?,?)";
		Object[] queryObjects={u.getUsername(),u.getRegisterTime()
				,u.getRole().toString()};
		
		jdbcTemplate.update(sql,queryObjects);
		
		//获取自增id
		List<Integer> l=jdbcTemplate.query(
				"select last_insert_id()"
				,new RowMapper<Integer>(){

					@Override
					public Integer mapRow(ResultSet rs, int arg1) 
							throws SQLException {
						return rs.getInt("last_insert_id()");
					}
				}
		);
		if(l==null || l.size()!=1)
			return null;
		else
			return l.get(0);
	}

	@Override
	public boolean update(User u) {
		String sql="update user set "+
				"username=?,register_time=?,role=? "+
				"where id=?";
		Object queryObjects=new Object[]{
				u.getUsername(),
				u.getRegisterTime(),
				u.getRole(),
				u.getId()};
		jdbcTemplate.update(sql,
				u.getUsername(),
				u.getRegisterTime(),
				u.getRole().toString(),
				u.getId());
		
		return true;
	}

	@Override
	public boolean delete(int id) {
		String sql="delete from user where id=?";
		jdbcTemplate.update(sql,new Object[]{id});
		
		return false;
	}

	@Override
	public User getByUsername(String username) {
		String sql="select * from user where username=?";
		List<User> u=jdbcTemplate.query(sql,new Object[]{username},
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User u=new User();
						u.setId(rs.getInt("id"));
						u.setUsername(rs.getString("username"));
						u.setRegisterTime(rs.getLong("register_time"));
						u.setRole(Role.fromString(rs.getString("role")));
						return u;
					}
				}
		);
		if(u==null || u.size()!=1) 
			return null;
		return 
			u.get(0);
	}

	
	

}
