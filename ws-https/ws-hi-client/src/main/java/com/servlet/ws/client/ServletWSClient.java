package com.servlet.ws.client;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.ResourceBundle;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import com.mycompany.cxf.jaxws.javafirst.HelloWorld;
import com.mycompany.cxf.jaxws.javafirst.HelloWorldImplService;

/**
 * Servlet implementation class ServletWSClient
 */
public class ServletWSClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletWSClient() {
        // TODO Auto-generated constructor stub
    }
	static String url;
	
	static {
		url=ResourceBundle.getBundle("ws").getString("url");
	}
	

    public void doProcess() {

    	bypassSSL();
    		HelloWorldImplService amServices = new HelloWorldImplService();

    		HelloWorld port = amServices.getHelloWorldImplPort();
    		// Without these next two lines, response won't come back ever again.
    		Map<String, Object> req_ctx = ((BindingProvider) port).getRequestContext();
    		req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

    		String response=port.sayHi("test1");
    		System.out.println(response);
    
    		
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess();
	}

}
