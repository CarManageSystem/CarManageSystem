package com.parkingmanage.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 
 * @author zhangx
 * @date 2015年12月30日
 */
public class MyUtils {
	
	public static String int2String(Integer n){
		if(n==null)
			return "";
		return n.toString();
	}
	
	public static JSONObject list2Json(List list){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("totalProperty", list.size());
		map.put("root", list);
		return JSONObject.fromObject(map);
	}
	
	/**
	 * 字符串编码 
	 * @param str
	 * @return
	 */
	public static String codeString(String str){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<str.length(); i++){
			sb.append( (char)(str.charAt(i)) );
		}
		return sb.toString();
	}
	
	/**
	 * 字符串解码
	 * @param str
	 * @return
	 */
	public static String decodeString(String str){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<str.length(); i++){
			sb.append( (char)(str.charAt(i)) );
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println( codeString("abcd") );
		System.out.println( decodeString("bcde") );
	}
}
