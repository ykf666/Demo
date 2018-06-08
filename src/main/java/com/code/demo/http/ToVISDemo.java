package com.code.demo.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToVISDemo {

	private final static String APPSECRET_STRING = "651958d6e25b45afabc8b5a5bc973498";

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// HttpGet httpGet = new HttpGet("http://httpbin.org/get");
			// CloseableHttpResponse response1 = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response
			// object
			// to allow the response content to be streamed directly from the
			// network socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally
			// clause.
			// Please note that if response content is not fully consumed the
			// underlying
			// connection cannot be safely re-used and will be shut down and
			// discarded
			// by the connection manager.
			// try {
			// System.out.println(response1.getStatusLine());
			// HttpEntity entity1 = response1.getEntity();
			// // do something useful with the response body
			// // and ensure it is fully consumed
			// EntityUtils.consume(entity1);
			// } finally {
			// response1.close();
			// }
			Map<String, String> params = new HashMap<String, String>();
			params.put("action", "order");
			params.put("ip", "218.247.6.242");
			params.put("module", "purchase");
			params.put("order_time", "1487058501");// 秒
			params.put("order_type", "1");// 1，账单订购；2，积分订购
			params.put("partner", "BOTT");
			params.put("ppv_code", "");
			params.put("price", "10");// 分
			params.put("product_code", "AHDXKDP_PRO_MONPACK");
			params.put("product_type", "2");// 1，PPV；2，节目包（PVOD或者SVOD）
			params.put("source", "test");
			params.put("stbid", "023155000802391");// 百视通账号

			HttpPost httpPost = new HttpPost("http://b2cdemo.bestv.com.cn/vasroot/viscore/upc/index/main");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("action", params.get("action")));
			nvps.add(new BasicNameValuePair("ip", params.get("ip")));
			nvps.add(new BasicNameValuePair("module", params.get("module")));
			nvps.add(new BasicNameValuePair("order_time", params.get("order_time")));
			nvps.add(new BasicNameValuePair("order_type", params.get("order_type")));
			nvps.add(new BasicNameValuePair("partner", params.get("partner")));
			nvps.add(new BasicNameValuePair("ppv_code", params.get("ppv_code")));
			nvps.add(new BasicNameValuePair("price", params.get("price")));
			nvps.add(new BasicNameValuePair("product_code", params.get("product_code")));
			nvps.add(new BasicNameValuePair("product_type", params.get("product_type")));
			nvps.add(new BasicNameValuePair("source", params.get("source")));
			nvps.add(new BasicNameValuePair("stbid", params.get("stbid")));
			nvps.add(new BasicNameValuePair("sign", getSign(params)));
			
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);
			try {
				System.out.println(response2.getStatusLine());
				System.out.println(EntityUtils.toString(response2.getEntity()));
				HttpEntity entity2 = response2.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity2);
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}
	}

	private static String getSign(Map<String, String> map) {
		try {
			String str = APPSECRET_STRING + map.get("action") + map.get("ip") + map.get("module") + map.get("order_time")
					+ map.get("order_type") + map.get("partner") + map.get("ppv_code") + map.get("price") + map.get("product_code")
					+ map.get("product_type") + map.get("source") + map.get("stbid");
			System.out.println("param--" + str);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			
			byte[] b = md.digest();
			int i;
			StringBuffer buf = new StringBuffer();
			for (int offset = 0; offset < b.length; offset++) {  
				i = b[offset];  
				if (i < 0)  
					i += 256;  
				if (i < 16)  
					buf.append("0");  
				buf.append(Integer.toHexString(i));  
			}
			String md5Str = buf.toString().toUpperCase();
			System.out.println("sign--" + md5Str);
			return md5Str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
