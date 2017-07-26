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
					public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserInfo u=new UserInfo(
								rs.getInt("id"),
								Gender.stringToEnum(rs.getString("gender")),
								rs.getString("photo_path"),
								rs.getString("personal_signature")
								);
						return u;
					}
				});
			if(u.size()!=1) return null;
			return u.get(0);
	}

	@Override
	public boolean add(UserInfo ui) {
		Object[] params={ui.getId(),Gender.enumToString(ui.getGender())
				,ui.getPhotoPath(),ui.getPersonalSignature()};
		jdbcTemplate.update("insert into user_info values(?,?,?,?)"
				,params);
		return true;
	}

	@Override
	public boolean update(UserInfo ui) {
		String sql="update user_info set "+
				"gender=?,photo_path=?,personal_signature=? "+
				"where id=?";
		Object params=new Object[]{
				Gender.enumToString(ui.getGender()),
				ui.getPhotoPath(),
				ui.getPersonalSignature(),
				ui.getId()};
		jdbcTemplate.update(sql,params);
		return true;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
