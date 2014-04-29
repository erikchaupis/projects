package com.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.mycompany.cxf.jaxws.javafirst.HelloWorld;

public class Test {

//	static String URL_WSDL;
	static String URL_WSDL="https://10.50.24.115:8443/cxf-jaxws-javafirst/HelloWorld?wsdl";
//	static String URL_WSDL="http://10.50.24.115:9090/cxf-jaxws-javafirst/HelloWorld?wsdl";
	
	static {
//		url=ResourceBundle.getBundle("ws").getString("url");
	}
	
	static private void call() {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){
 
	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	            if (hostname.equals("localhost")) {
	                return true;
	            }
	            return false;
	        }
	    });
	}
	
	public static void main(String[] args) throws MalformedURLException {
		bypassSSL();
//		call();
		URL url = new URL(URL_WSDL);
        QName qname = new QName("http://javafirst.jaxws.cxf.mycompany.com/", "HelloWorldImplService");
 
        Service service = Service.create(url, qname);
        HelloWorld hello = service.getPort(HelloWorld.class);

        String response=hello.sayHi("test1");
		System.out.println(response);
//		HelloWorldImplService amServices = new HelloWorldImplService();
//
//		HelloWorld port = amServices.getHelloWorldImplPort();
//		// Without these next two lines, response won't come back ever again.
//		Map<String, Object> req_ctx = ((BindingProvider) port).getRequestContext();
//		req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
//
//		String response=port.sayHi("test1");
//		System.out.println(response);

		
	}
	
	
	private static void bypassSSL()
	  {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
	    {
	      public boolean verify(String hostname, SSLSession sslSession)
	      {
	        return true;
	      }
	    });
	    TrustManager[] trustAllCerts = { new X509TrustManager()
	    {
	      public X509Certificate[] getAcceptedIssuers()
	      {
	        return null;
	      }

	      public void checkClientTrusted(X509Certificate[] certs, String authType)
	      {
	      }

	      public void checkServerTrusted(X509Certificate[] certs, String authType)
	      {
	      }
	    }
	     };
	    try
	    {
	      SSLContext sc = SSLContext.getInstance("SSL");
	      sc.init(null, trustAllCerts, new SecureRandom());
	      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    } catch (KeyManagementException ex) {
//	      org.apache.log4j.Logger.getLogger(Main.class.getName()).log(org.apache.log4j.Level.ERROR, null, ex);
	    } catch (NoSuchAlgorithmException ex) {
//	      org.apache.log4j.Logger.getLogger(Main.class.getName()).log(org.apache.log4j.Level.ERROR, null, ex);
	    }
	  }
	
	
}
