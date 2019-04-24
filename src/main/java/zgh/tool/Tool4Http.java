package zgh.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送HTTP请求的工具类
 * @author zhangguihua(hua7381@163.com)
 * @date: 2018年12月12日
 */
public class Tool4Http {

	private static final Integer CONNECT_TIMEOUT = 1000 * 2;
	private static final Integer READ_TIMEOUT = 1000 * 5;

	public static String sendPost(String url) {
		return sendPost(url, new HashMap(), CONNECT_TIMEOUT, READ_TIMEOUT);
	}

	public static String sendPost(String url, int connectTimeout, int readTimeout) {
		return sendPost(url, new HashMap(), connectTimeout, readTimeout);
	}

	/**
	 * 发送post请求
	 * @param url 请求地址
	 * @param params 参数键值对
	 * @return
	 */
	public static String sendPost(String url, Map params) {
		return sendPost(url, params, CONNECT_TIMEOUT, READ_TIMEOUT);
	}

	/**
	 * 发送post请求
	 * @param url 请求地址
	 * @param params 参数键值对
	 * @param connectTimeout 请求超时时间
	 * @param readTimeout 返回超时时间
	 * @return
	 */
	public static String sendPost(String url, Map params, int connectTimeout, int readTimeout) {
		StringBuffer sb = new StringBuffer();
		for (Object key : params.keySet()) {
			Object val = params.get(key);
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(key + "=" + val);
		}
		return sendPost(url, sb.toString(), connectTimeout, readTimeout);
	}

	public static String sendPost(String url, String param) {
		return sendPost(url, param, CONNECT_TIMEOUT, READ_TIMEOUT);
	}

	private static String sendPost(String url, String param, int connectTimeout, int readTimeout) {

		String result = "";
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 设置超时时间
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 读取响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {// 关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String sendGet(String url) {
		return sendGet(url, "", CONNECT_TIMEOUT, READ_TIMEOUT);
	}

	public static String sendGet(String url, int connectTimeout, int readTimeout) {
		return sendGet(url, "", connectTimeout, readTimeout);
	}

	public static String sendGet(String url, Map params) {
		return sendGet(url, params, CONNECT_TIMEOUT, READ_TIMEOUT);
	}

	public static String sendGet(String url, Map params, int connectTimeout, int readTimeout) {
		try {
			StringBuffer sb = new StringBuffer();
			for (Object key : params.keySet()) {
				Object val = params.get(key);
				if (sb.length() > 0) {
					sb.append("&");
				}
				sb.append(key + "=" + URLEncoder.encode("" + val, "utf-8"));
			}
			return sendGet(url, sb.toString(), connectTimeout, readTimeout);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String sendGet(String url, String param) {
		return sendGet(url, param, CONNECT_TIMEOUT, READ_TIMEOUT);
	}

	public static String sendGet(String url, String param, int connectTimeout, int readTimeout) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 设置超时时间
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);

			connection.connect();// 建立连接
			// 读取响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
