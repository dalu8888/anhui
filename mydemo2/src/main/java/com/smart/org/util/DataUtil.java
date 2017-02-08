package com.smart.org.util;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;

public class DataUtil {

   private static Gson gson = new Gson();
	
   public static String signRequest(String appid,Object body,String appkey)throws Exception{
	   
	    String srcText = gson.toJson(body);
		// 对报文进行BASE64编码，避免中文处理问题
		String base64Text = new String(Base64.encodeBase64((appid + srcText).getBytes("utf-8"), false));
		// MD5摘要，生成固定长度字符串用于加密
		String destText = MD5Util.md5Digest(base64Text);
		AlgorithmData data = new AlgorithmData();
		data.setDataMing(destText);
		data.setKey(appkey);
		// 3DES加密
		Algorithm3DES.encryptMode(data);
		return data.getDataMi();
   }
   

	
}