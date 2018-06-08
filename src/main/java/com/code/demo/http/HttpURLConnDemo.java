package com.code.demo.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpURLConnDemo {

	public static void main(String[] args) {
		String urlPath = new String("http://b2cdemo.bestv.com.cn/vasroot/viscore/upc/index/main");
		String param = "sign=525E5263C89F1F5FBC48ABC5A553D946&module=purchase&price=0&source=&order_time=1487162260"
				+ "&product_code=05903500030001&stbid=023155000802391&action=order&product_type=1"
				+ "&partner=BOTT&ip=127.0.0.1&order_type=1&ppv_code=2476256";
		try {
			// 建立连接
			URL url = new URL(urlPath);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			// 设置参数
			httpConn.setDoOutput(true); // 需要输出
			httpConn.setDoInput(true); // 需要输入
			httpConn.setUseCaches(false); // 不允许缓存
			httpConn.setRequestMethod("POST"); // 设置POST方式连接
			// 设置请求属性
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");
			// 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
			httpConn.connect();
			// 建立输入流，向指向的URL传入参数
			DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			// 获得响应状态
			int resultCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				System.out.println(sb.toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
