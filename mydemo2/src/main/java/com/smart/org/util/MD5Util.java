package com.smart.org.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.Arrays;

public class MD5Util {

	public static String md5Digest(String pwd)throws Exception{
		try{
	      MessageDigest alg = MessageDigest.getInstance("MD5");
	      alg.update(pwd.getBytes("UTF-8"));
	      byte[] digest = alg.digest();
	      return byte2hex(digest);
	    } catch (Exception e) {
	      System.out.println(e.toString());
	    }return "";
	}
	
	private static String byte2hex(byte[] b)
	  {
	    String hs = "";
	    String stmp = "";
	    for (int n = 0; n < b.length; n++) {
	      stmp = Integer.toHexString(b[n] & 0xFF);
	      if (stmp.length() == 1)
	        hs = hs + "0" + stmp;
	      else
	        hs = hs + stmp;
	    }
	    return hs.toLowerCase();
	  }
//	public static void main(String[] args)throws Exception{
//		System.out.println(MD5Util.md5Digest("123456uiyouiutiuyiuyiuyuiyuiyiuyiyiuyuytyuytuytuy"));
//	}
	/**
	 * 把参数排序,然后按算法加密
	 * @param apikey
	 * @param secretkey
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String signGetParam(String apikey,String secretkey,String param) throws Exception{
		String pf = "";
		if(param != null && !param.equals("")){
			String[] ps = param.split("&");
			Arrays.sort(ps,String.CASE_INSENSITIVE_ORDER);
			for (String p:ps) {
				pf += p+"&";
			}
			pf = pf.substring(0,pf.length()-1);
		}
		return signRequest(apikey, pf, secretkey);
	}

	public static String signRequest(String apikey,String srcText,String secretkey)throws Exception{

		// 对报文进行BASE64编码，避免中文处理问题
		String base64Text = new String(Base64.encodeBase64((apikey + srcText)
				.getBytes("utf-8"), false));
		// MD5摘要，生成固定长度字符串用于加密
		String destText = MD5Util.md5Digest(base64Text);
		AlgorithmData data = new AlgorithmData();
		data.setDataMing(destText);
		data.setKey(secretkey);
		// 3DES加密
		Algorithm3DES.encryptMode(data);
		return data.getDataMi();
	}
}
