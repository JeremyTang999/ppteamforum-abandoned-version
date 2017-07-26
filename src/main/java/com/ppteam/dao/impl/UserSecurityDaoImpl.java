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
import com.ppteam.entity.Role;
import com.ppteam.entity.User;
import com.ppteam.entity.UserSecurity;

@Repository("UserSecurityDao")
public class UserSecurityDaoImpl implements UserSecurityDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserSecurity get(int id) {
		List<UserSecurity> u=jdbcTemplate.query("select * from user_security where id=?",new Object[]{id},
				new RowMapper<UserSecurity>() {
					@Override
					public UserSecurity mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserSecurity u=new UserSecurity();
						u.setId(rs.getInt("id"));
						u.setPassword(rs.getString("password"));
						Map<String,String> m=new HashMap<String,String>();
						m.put(rs.getString("question1"),rs.getString("answer1"));
						m.put(rs.getString("question2"),rs.getString("answer2"));
						m.put(rs.getString("question3"),rs.getString("answer3"));
						u.setQuestionsAndAnswers(m);
						return u;
					}
				});
			if(u.size()!=1) return null;
			return u.get(0);
	}

	@Override
	public boolean add(UserSecurity us) {
		Map<String,String> m=us.getQuestionsAndAnswers();
		Set<String> keys=m.keySet();
		String[] q=new String[3];
		Iterator<String> it=keys.iterator();
		for(int i=0;i<3;i++){
			if(it.hasNext()){
				q[i]=it.next();
			}
		}
		/*if(keys.size()==3){
			for(int i=0;i<3;i++){
				if(it.hasNext()){
					q[i]=it.next();
				}
			}
		}*/
		
		System.out.println(m);
		System.out.println(keys);
		System.out.println(q.toString());
		Object[] params={
				us.getId(),
				us.getPassword(),
				q[0],(q[0]!=null)?(m.get(q[0])):null,
				q[1],(q[1]!=null)?(m.get(q[1])):null,
				q[2],(q[2]!=null)?(m.get(q[2])):null
		};
		jdbcTemplate.update("insert into user_security values(?,?,?,?,?,?,?,?)"
				,params);
		return true;
	}

	@Override
	public boolean update(UserSecurity us) {
		Map<String,String> m=us.getQuestionsAndAnswers();
		Set<String> keys=m.keySet();
		String[] q=new String[3];
		Iterator<String> it=keys.iterator();
		for(int i=0;i<3;i++){
			if(it.hasNext()){
				q[i]=it.next();
			}
		}

		
		
		Object[] params={
				us.getPassword(),
				q[0],(q[0]!=null)?(m.get(q[0])):null,
				q[1],(q[1]!=null)?(m.get(q[1])):null,
				q[2],(q[2]!=null)?(m.get(q[2])):null,
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
		// TODO Auto-generated method stub
		return true;
	}

}
