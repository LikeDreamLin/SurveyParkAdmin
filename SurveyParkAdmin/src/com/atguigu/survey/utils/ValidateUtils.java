package com.atguigu.survey.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.atguigu.survey.guest.entity.Bag;

public class ValidateUtils {
	
	public static boolean mapValidate(Map map) {
		return map != null && map.size() > 0;
	}
	
	/**
	 * 检查数组是否为空
	 * @param arr
	 * @return
	 */
	public static boolean arrayValidate(Object [] arr) {
		return arr != null && arr.length > 0;
	}
	
	/**
	 * 检查集合对象是否为空，null和size为0都看做是空
	 * @param c
	 * @return
	 */
	public static boolean collectionValidate(Collection c) {
		return c != null && c.size() > 0;
	}

	/**
	 * 检查字符串是否有效
	 * @param logoPath
	 * @return
	 */
	public static boolean stringValidate(String logoPath) {
		return logoPath != null && logoPath.length() > 0;
	}

	/**
	 * 检查Bag对象中bagOrder是否有重复数据
	 * @param bagList
	 * @return
	 */
	public static boolean bagListValidate(List<Bag> bagList) {
		
		/*
		 * 将所有bagOrder依次取出，存放到一个Set集合中，然后比较Set集合和bagList的长度
		 * 如果长度不一致，说明其中包含重复数据
		 *Collection
		 *	List 有顺序，可以重复
		 *	Set	 没有顺序，不可重复
		 *Map 
		 */
		Set<Integer> orderSet = new HashSet<>();
		for (Bag bag : bagList) {
			int bagOrder = bag.getBagOrder();
			
			orderSet.add(bagOrder);
			
		}
		
		return orderSet.size() == bagList.size();
	}
	
	

}
