package com.atguigu.survey.base.i;

import java.util.List;

/**
 * Dao基类所需要具备的功能列表
 * @author Creathin
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	//insert into bags(bag_name,bag_order) values(?,?) Object[]
	//insert into bags(bag_name,bag_order) values(?,?) Object[]
	//insert into bags(bag_name,bag_order) values(?,?) Object[]
	//insert into bags(bag_name,bag_order) values(?,?) Object[]
	//insert into bags(bag_name,bag_order) values(?,?) Object[]
	//insert into bags(bag_name,bag_order) values(?,?) Object[]
	
	//DBUtils qureyRunner.batch(String sql,Object[][]);
	//Spring JDBCTemplate tempalte.batchUpdate(String sql,List<Object[]> params);
	void doBatchWork(String sql, List<Object[]> params);
	
	/**
	 * 保存和更新兼容的方法
	 * @param t
	 */
	void saveOrUpdateEntity(T t);
	
	/**
	 * 保存一个实体对象
	 * @param t
	 */
	void saveEntity(T t);
	
	/**
	 * 根据实体删除对象
	 * @param t
	 */
	void deleteEntity(T t);
	
	/**
	 * 根据实体对象更新数据库表中的记录
	 * @param t
	 */
	void updateEntity(T t);
	
	/**
	 * 根据OID的值查询对应的实体对象
	 * @param entityId
	 * @return
	 */
	T getEntityById(Integer entityId);
	
	/**
	 * 根据实体类对象的类型查询全部数据
	 * @return
	 */
	List<T> getEntityList();

}
