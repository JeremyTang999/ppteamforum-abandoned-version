/*package com.ppteam.dao.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ppteam.dao.BaseDao;


public class BaseDaoImpl<T> implements BaseDao<T> {
	
	Class<T> clazz;
	Field[] fields;
	List<String> fieldsName=new ArrayList<String>();
	List<String> sqlRowsName=new ArrayList<String>();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public BaseDaoImpl(Class<T> clazz) {
		this.clazz=clazz;
		fields=clazz.getDeclaredFields();
		for(Field f:fields){
			fieldsName.add(f.getName());
			sqlRowsName.add(fieldNameToRowName(f.getName()));
		}
	}
	
	private String fieldNameToRowName(String fieldName){
		StringBuffer buffer=new StringBuffer(fieldName);
		char c;
		for(int i=0;i<buffer.length();i++){
			c=buffer.charAt(i);
			if(Character.isUpperCase(c)){
				buffer.setCharAt(i,Character.toLowerCase(c));
				buffer.insert(i,"_");
				i++;
			}
		}
		return buffer.toString();
	}
	
	private String classNameToTableName(String className){
		StringBuffer buffer=new StringBuffer(className);
		buffer.setCharAt(0,Character.toLowerCase(className.charAt(0)));
		return fieldNameToRowName(buffer.toString());
	}

	@Override
	public T get(int id) {
		try {
			
			
			
			String sql="select * from "+classNameToTableName(clazz.getSimpleName())+
					" where id=?";
			
			List<T> l=jdbcTemplate.query(sql
					,new Object[]{id}
					,new MyRowMapper());
			if(l.size()==1){
				return l.get(0);
			}
			else{
				return null;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	class MyRowMapper implements RowMapper<T>{

		@Override
		public T mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			try {
				T t=clazz.newInstance();
				for(Field field:fields){
					field.setAccessible(true);
					if(field.getType().equals(Long.class)){
						rs.getString(fieldNameToRowName(field.getName()));
					}
					else if(field.getType().equals(Integer.class)){
						rs.getInt(fieldNameToRowName(field.getName()));
					}
					else{
						rs.getString(fieldNameToRowName(field.getName()));
					}
				}
				return t;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}

}
*/