package com.ppteam.dao;

public interface BaseDao<T>{
	public T get(int id);
	public Integer add(T t);
	public boolean update(T t);
	public boolean delete(int id);
}
