package springboot.checkCodeUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		// TODO Auto-generated method stub

	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return new X509Certificate[] {};
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	public static void main(String[] args) {
		StringBuffer buffer = null;
		try {
			// 创建SSLContext
			System.setProperty("https.protocols", "TLSv1");
			SSLContext sslContext = SSLContext.getInstance("TLSv1");
			TrustManager[] tm = { new MyX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			;
			// 获取SSLSocketFactory对象
			javax.net.ssl.SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(
					"https://sms-activate.ru/stubs/handler_api.php?api_key=4394c2194b1f6A4Ac91bfb59A31d8ede&action=getNumber&service=fb&country=0");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			// 设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			// 往服务器端写内容
			// if(null!=outputStr){
			// OutputStream os=conn.getOutputStream();
			// os.write(outputStr.getBytes("utf-8"));
			// os.close();
			// }

			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(buffer.toString());
	}
}
