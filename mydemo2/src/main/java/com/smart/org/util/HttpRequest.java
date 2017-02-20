package com.smart.org.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by asus on 2017/1/19.
 */
public class HttpRequest {

    public static String apikey="ZGlnaXRhbGNoaW5hMTAwMA==";
    public static String secretkey= "5923732ac18bc9a3de2c9c71e3c521f3c18bc9a3de2c9c71";
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @paramparam
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String urlParams) throws IOException {
        GetMethod get = null;
        String rtstring=null;
        if (urlParams != null && !"".equals(urlParams)) {
            get = new GetMethod(url + "?" + urlParams);
        } else {
            get = new GetMethod(url);
        }
        HttpClient client = new HttpClient();
        try {
            get.setRequestHeader("apikey", apikey);
            get.setRequestHeader("sign", signGetParam(apikey, secretkey, urlParams));
            client.executeMethod(get);
            rtstring=get.getResponseBodyAsString();
        } catch (final Exception e) {
            e.printStackTrace();
            // 调用异常, 返回异常报文
        } finally {
            get.releaseConnection();
        }
        return rtstring;
    }

    public static String sendGet2(String url, String urlParams) throws IOException {
        GetMethod get = null;
        String rtstring=null;
        if (urlParams != null && !"".equals(urlParams)) {
            get = new GetMethod(url + "?" + urlParams);
        } else {
            get = new GetMethod(url);
        }
        HttpClient client = new HttpClient();
        try {
//            get.setRequestHeader("apikey", apikey);
//            get.setRequestHeader("sign", signGetParam(apikey, secretkey, urlParams));
            client.executeMethod(get);
            rtstring=get.getResponseBodyAsString();
        } catch (final Exception e) {
            e.printStackTrace();
            // 调用异常, 返回异常报文
        } finally {
            get.releaseConnection();
        }
        return rtstring;
    }

    /**
     * 把参数排序,然后按算法加密
     *
     * @param apikey
     * @param secretkey
     * @param param
     * @return
     * @throws Exception
     */
    public static String signGetParam(String apikey, String secretkey, String param) throws Exception {
        String pf = "";
        if (param != null && !param.equals("")) {
            String[] ps = param.split("&");
            Arrays.sort(ps, String.CASE_INSENSITIVE_ORDER);
            for (String p : ps) {
                pf += p + "&";
            }
            pf = pf.substring(0, pf.length() - 1);
        }

        return signRequest(apikey, pf, secretkey);
    }

    public static String signRequest(String appid, String srcText, String appkey) throws Exception {

        // 对报文进行BASE64编码，避免中文处理问题
        String base64Text = new String(org.apache.commons.codec.binary.Base64.encodeBase64((appid + srcText)
                .getBytes("utf-8"), false));
        // MD5摘要，生成固定长度字符串用于加密
        String destText = MD5Util.md5Digest(base64Text);
        AlgorithmData data = new AlgorithmData();
        data.setDataMing(destText);
        data.setKey(appkey);
        // 3DES加密
        Algorithm3DES.encryptMode(data);
        return data.getDataMi();
    }

//    public static void main(String[] args) throws IOException {
//        String address = "http://apis.scity.cn/fengbao/riskstromdata/search";
//        String urlParams = "keyword=" + URLEncoder.encode("神码","utf-8") + "&from=" + 1 + "&size=" + 10;
////        urlParams = new URI(urlParams, false, "UTF-8").toString();
////        String json = commonGet(address, urlParams);
//        String json = HttpRequest.sendGet(address, urlParams);
//    }



}
