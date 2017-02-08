package com.smart.org.util;

import java.util.Iterator;
import java.util.Map;

public class JsonStrParam {
	@SuppressWarnings("rawtypes")
	public static String buildJsonStrParam(Map<String, Object> params){
		String jsonsp=null;
		String PRESTR="{body:{";
		String LASTSTR="}";
		String midstr="";
		Iterator it = params.entrySet().iterator();
		 while (it.hasNext()) {
			    Map.Entry entry = (Map.Entry) it.next();
			    Object key = entry.getKey();
			    Object value = entry.getValue();
//			    if(null!=value){
//			    if(("startHour".equals(key)&&"".equals(value))||("endHour".equals(key)&&"".equals(value))){
//			    	midstr+="'"+key+"'"+":"+"'"+value+"'"+",";
//			    }
			    System.out.println("key=" + key + " value=" + value);
			    midstr+="'"+key+"'"+":"+"'"+value+"'"+",";
			   }
		 midstr=midstr.substring(0, midstr.length()-1);
		 jsonsp=PRESTR+midstr+LASTSTR+",'head':{}}";
		 System.out.println(jsonsp);
		return jsonsp;
	}

}
