package com.ppteam.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ppteam.dao.UserInfoDao;
import com.ppteam.entity.Gender;
import com.ppteam.entity.Role;
import com.ppteam.entity.User;
import com.ppteam.entity.UserInfo;

@Repository("UserInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public UserInfo get(int id) {
		List<UserInfo> u=jdbcTemplate.query("select * from user_info where id=?",new Object[]{id},
				new RowMapper<UserInfo>() {
					@Override
					public UserInfo mapRow(ResultSet rs, int rowNum) 
							throws SQLException {
						UserInfo u=new UserInfo();
						u.setId(rs.getInt("id"));
						u.setGender(Gender.fromString(rs.getString("gender")));
						u.setPhotoPath(rs.getString("photo_path"));
						u.setPersonalSignature(rs.getString("personal_signature"));
						return u;
					}
				}
		);
			
		if(u.size()!=1) 
			return null;
		else
			return u.get(0);
	}

	@Override
	public Integer add(UserInfo ui) {
		String sql="insert into user_info values(?,?,?,?)";
		Object[] queryObjects={ui.getId(),ui.getGender().toString()
				,ui.getPhotoPath(),ui.getPersonalSignature()};
		jdbcTemplate.update(sql,queryObjects);
		
		//user_info表id字段无自增，对应user表id字段，
		//直接从实体获取
		return ui.getId();
	}

	@Override
	public boolean update(UserInfo ui) {
		String sql="update user_info set "+
				"gender=?,photo_path=?,personal_signature=? "+
				"where id=?";
		Object queryObjects=new Object[]{
				ui.getGender().toString(),
				ui.getPhotoPath(),
				ui.getPersonalSignature(),
				ui.getId()};
		jdbcTemplate.update(sql,queryObjects);
		
		return true;
	}

	@Override
	public boolean delete(int id) {
		
		//暂时无需使用此方法
		return false;
	}

}
