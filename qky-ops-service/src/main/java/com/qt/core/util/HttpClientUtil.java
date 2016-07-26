package com.qt.core.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	private static final Log logger = LogFactory.getLog(HttpClientUtil.class.getName());

	public static String post(String url, String reqMsg, RequestConfig requestConfig) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String responseBody = null;
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(reqMsg, ContentType.create("text/plain", "UTF-8")));

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};

			if (requestConfig != null) {
				httpPost.setConfig(requestConfig);
			}
			responseBody = httpclient.execute(httpPost, responseHandler);

		} catch (ClientProtocolException e) {
			logger.error(null, e);
		} catch (IOException e) {
			logger.error(null, e);
			throw e;
		} finally {
			logger.info("[post]:" + (httpPost != null ? httpPost.getRequestLine() : "") + " [request] :" + reqMsg
					+ " [response]:" + responseBody);
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(null, e);
			}
		}
		return responseBody;
	}

	public static String post(String url, List<NameValuePair> nvps) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String responseBody = "";
		HttpPost httpPost = null;
		try {

			httpPost = new HttpPost(url);

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			response = httpclient.execute(httpPost);

			HttpEntity entity2 = response.getEntity();

			responseBody = EntityUtils.toString(entity2);

			// do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(entity2);
		} catch (UnsupportedEncodingException e1) {
			logger.error(null, e1);
		} catch (ClientProtocolException e1) {
			logger.error(null, e1);
		} catch (IOException e1) {
			logger.error(null, e1);
		} finally {
			logger.info(" [post]:" + (httpPost != null ? httpPost.getRequestLine() : "") + " [request]:"
					+ (nvps != null ? nvps.toString() : "") + " [response status]:"
					+ (response != null ? response.getStatusLine() : "") + " [response content]:" + responseBody);
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				logger.error(null, e);
			}
		}
		return responseBody;
	}

	/**
	 * 发送Get请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, List<NameValuePair> params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String body = null;
		try {
			// Get请求
			HttpGet httpget = new HttpGet(url);
			if(params != null){
				// 设置参数
				String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
				httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
			}
			// 发送请求
			HttpResponse httpresponse = httpclient.execute(httpget);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);
			if (entity != null) {
				entity.consumeContent();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return body;
	}

	public static int returnStatus(String url, String type, RequestConfig requestConfig) {
		CloseableHttpClient client = HttpClients.createDefault();
		int status = 0;
		CloseableHttpResponse httpRsp = null;
		try {
			HttpRequestBase httpRequest = null;
			if ("GET".equals(type.toUpperCase())) {
				httpRequest = new HttpGet(url);
			} else if ("POST".equals(type.toUpperCase())) {
				httpRequest = new HttpPost(url);
			} else {
				return status;
			}
			if (requestConfig != null && httpRequest != null) {
				httpRequest.setConfig(requestConfig);
			}
			httpRsp = client.execute(httpRequest);
			status = httpRsp.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			logger.error(null, e);
		} catch (IOException e) {
			logger.error(null, e);
		} finally {
			try {
				if (httpRsp != null) {
					httpRsp.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				logger.error(null, e);
			}
		}

		return status;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// String url = "http://218.204.254.98:18081/services/eduSOAP?wsdl";
		String url = "http://192.168.210.150:8082/services/";
		/**
		 * List list = new ArrayList(); NameValuePair nvps = new
		 * BasicNameValuePair("a","b"); list.add(nvps); String str = null; //
		 * String str = HttpClientUtil.post(
		 * "http://211.137.75.2241/xxt3hb_healthy_check.do?action=index", list);
		 * 
		 * try { str = HttpClientUtil.post(
		 * "http://218.204.254.98:18081/services/eduSOAP?wsdl", "",null); }
		 * catch (IOException e) { e.printStackTrace(); }
		 * 
		 * System.out.print(str);
		 **/
		System.out.print(returnStatus(url, "POST", null));

	}
}
