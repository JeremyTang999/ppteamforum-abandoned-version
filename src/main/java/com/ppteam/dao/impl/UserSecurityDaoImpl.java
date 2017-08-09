package com.ppteam.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ppteam.dao.UserSecurityDao;
import com.ppteam.entity.Gender;
import com.ppteam.entity.QuestionAndAnswer;
import com.ppteam.entity.Role;
import com.ppteam.entity.User;
import com.ppteam.entity.UserSecurity;

@Repository("UserSecurityDao")
public class UserSecurityDaoImpl implements UserSecurityDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserSecurity get(int id) {
		String sql="select * from user_security where id=?";
		
		List<UserSecurity> u=jdbcTemplate.query(
			sql,new Object[]{id},
			new RowMapper<UserSecurity>() {
				@Override
				public UserSecurity mapRow(ResultSet rs, int rowNum) 
						throws SQLException {
					UserSecurity u=new UserSecurity();
					u.setId(rs.getInt("id"));
					u.setPassword(rs.getString("password"));
					List<QuestionAndAnswer> qnal=
						new ArrayList<QuestionAndAnswer>();
					QuestionAndAnswer qna=null;
					for(int i=0;i<3;i++){
						qna=new QuestionAndAnswer(
								rs.getString("question"+(i+1)),
								rs.getString("answer"+(i+1))
						);
						qnal.add(qna);
					}
					u.setQuestionsAndAnswers(qnal);
					return u;
				}
			}
		);
		if(u.size()!=1) return null;
		return u.get(0);
	}

	@Override
	public Integer add(UserSecurity us) {
		
		
		List<QuestionAndAnswer> qnal=us.getQuestionsAndAnswers();
		String[] q=new String[3];
		String[] a=new String[3];
		
		for(int i=0;i<3;i++){
			q[i]=qnal.get(i).getQuestion();
			a[i]=qnal.get(i).getAnswer();
		}
		

		Object[] queryObjects={
			us.getId(),
			us.getPassword(),
			q[0],a[0],
			q[1],a[1],
			q[2],a[2]
		};
		
		jdbcTemplate.update(
				"insert into user_security values(?,?,?,?,?,?,?,?)"
				,queryObjects);
		
		//同userinfo，id字段对应user的id字段，不自增
		return us.getId();
	}

	@Override
	public boolean update(UserSecurity us) {
	
		List<QuestionAndAnswer> qnal=us.getQuestionsAndAnswers();
		
		String[] q=new String[3];
		String[] a=new String[3];
		
		for(int i=0;i<3;i++){
			q[i]=qnal.get(i).getQuestion();
			a[i]=qnal.get(i).getAnswer();
		}
		

		Object[] params={
			us.getPassword(),
			q[0],a[0],
			q[1],a[1],
			q[2],a[2],
			us.getId()
		};
		
		String sql="update user_security set "+
				"password=?,"+
				"question1=?,answer1=?,"+
				"question2=?,answer2=?,"+
				"question3=?,answer3=? "+
				"where id=?";
		jdbcTemplate.update(sql,params);
		return true;
	}

	@Override
	public boolean delete(int id) {
		
		return false;
	}

}
