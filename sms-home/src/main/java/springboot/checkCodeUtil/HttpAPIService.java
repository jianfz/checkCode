package springboot.checkCodeUtil;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpAPIService {
	@Autowired
	private CloseableHttpClient httpClient;

	@Autowired
	private RequestConfig config;

	/**
	 * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url) throws Exception {
		// 声明 http get 请求
		HttpGet httpGet = new HttpGet(url);

		config.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
		// 装载配置信息
		httpGet.setConfig(config);

		// 发起请求
		CloseableHttpResponse response = this.httpClient.execute(httpGet);

		// 判断状态码是否为200
		if (response.getStatusLine().getStatusCode() == 200) {
			// 返回响应体的内容
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		}
		return null;
	}

	/**
	 * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url, Map<String, Object> map) throws Exception {
		URIBuilder uriBuilder = new URIBuilder(url);

		if (map != null) {
			// 遍历map,拼接请求参数
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				uriBuilder.setParameter(entry.getKey(), entry.getValue()
						.toString());
			}
		}

		// 调用不带参数的get请求
		return this.doGet(uriBuilder.build().toString());

	}

	/**
	 * 带参数的post请求
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url, Map<String, Object> map)
			throws Exception {
		// 声明httpPost请求
		HttpPost httpPost = new HttpPost(url);
		// 加入配置信息
		httpPost.setConfig(config);

		// 判断map是否为空，不为空则进行遍历，封装from表单对象
		if (map != null) {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue().toString()));
			}
			// 构造from表单对象
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
					list, "UTF-8");

			// 把表单放到post里
			httpPost.setEntity(urlEncodedFormEntity);
		}

		// 发起请求
		CloseableHttpResponse response = this.httpClient.execute(httpPost);
		return new HttpResult(response.getStatusLine().getStatusCode(),
				EntityUtils.toString(response.getEntity(), "UTF-8"));
	}

	/**
	 * 不带参数post请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url) throws Exception {
		return this.doPost(url, null);
	}

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	public String getHttpsClient(String url) throws ClientProtocolException,
			IOException {
		// 从工具方法中获得对应的可以访问Https的httpClient
		CloseableHttpClient httpClient = createSSLClientDefault();

		HttpGet httpGet = new HttpGet(url);
		// 自己先在浏览器登录一下，自行复制具体的Cookie
		// httpGet.setHeader("Cookie",
		// "HS_ETS_SID=4jSFY2wWwT0gPrWJ45ly!-1286216704; Null=31111111.51237.0000; logtype=2; certtype=0; certNo=33****************; isorgloginpage_cookie=0; hs_etrading_customskin=app_css");

		// 设置代理，方便Fiddle捕获具体信息
		// RequestConfig config=RequestConfig.custom()
		// .setProxy(HttpHost.create("127.0.0.1:8888"))
		// .build();
		httpGet.setConfig(config);
		// 执行get请求，获得对应的响应实例
		CloseableHttpResponse response = httpClient.execute(httpGet);

		// 打印响应的到的html正文
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity);
		System.out.println(html);

		// 关闭连接
		response.close();
		httpClient.close();
		return html;
	}
}
