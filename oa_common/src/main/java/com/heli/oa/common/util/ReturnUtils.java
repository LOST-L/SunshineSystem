package com.heli.oa.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


public class ReturnUtils{
	
	/**
	 * @author returnJSON 返回JSON格式数据
	 * @param response 响应 HttpServletResponse
	 * @param html 返回文本
	 * @return 响应成功（true）或者失败（false�?
	 * */
	@SuppressWarnings("finally")
	public static boolean reutnrJSON(HttpServletResponse response,String html){
		boolean flag = false;
		PrintWriter print = null;
		try {
			print = response.getWriter();
			print.print(html);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new RuntimeException("返回html时发生错误请检查："+e.getMessage());
		}finally {
			if (print != null) {
				print.flush();
				print.close();
			}
			return flag;
		}
		
	}
	/**
	 * @param o �?要转换成JSON的对�?
	 * */
	public static String stringToJson(Object o){
		return JSONObject.toJSONString(o);
    }
	
	
	//降序排序
		public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map){
	        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
	        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
	        {
	            @Override
	            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
	            {
	                int compare = (o1.getValue()).compareTo(o2.getValue());
	                return -compare;
	            }
	        });

	        Map<K, V> result = new LinkedHashMap<K, V>();
	        for (Map.Entry<K, V> entry : list) {
	            result.put(entry.getKey(), entry.getValue());
	        }
	        return result;
	    }
		
		//升序排序	
		public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueAscending(Map<K, V> map) {
	        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
	        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
	        {
	            @Override
	            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
	            {
	                int compare = (o1.getValue()).compareTo(o2.getValue());
	                return compare;
	            }
	        });

	        Map<K, V> result = new LinkedHashMap<K, V>();
	        for (Map.Entry<K, V> entry : list) {
	            result.put(entry.getKey(), entry.getValue());
	        }
	        return result;
	    }
}
