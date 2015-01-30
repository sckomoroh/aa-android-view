package com.communicator;

import com.communicator.ntlm.NTLMSchemeFactory;
import com.serialization.XmlDeserializer;
import com.serialization.XmlSerializer;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.Map;

/**
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 6:49 PM
 */
public class Invoker
{
    private String userName;
    private String password;
    private String domain;
    private HttpClient httpClient;

    private String urlPrefix;

    public Invoker(String urlPrefix, String userName, String domain, String password)
    {
        this.urlPrefix = urlPrefix;
        this.userName = userName;
        this.password = password;
        this.domain = domain;

        prepareTrustManagement();
    }

    public <T> T invokeManagementMethodGet(Map<String, String> args) throws Exception
    {
        final StackTraceElement[] stackElementTrace = Thread.currentThread().getStackTrace();
        String methodName = stackElementTrace[3].getMethodName();
        Class managementClass = Class.forName(stackElementTrace[3].getClassName());

        Method[] methods = managementClass.getMethods();

        Method managementMethod = null;
        for (Method method : methods)
        {
            if (method.getName().equals(methodName))
            {
                managementMethod = method;
                break;
            }
        }

        if (managementMethod == null)
        {
            throw new InvokerException("The method was not found, that is should not be happened");
        }

        ManagementService service = (ManagementService) managementClass.getAnnotation(ManagementService.class);
        if (service == null)
        {
            throw new InvokerException("Management service not found");
        }

        ManagementMethod method = managementMethod.getAnnotation(ManagementMethod.class);
        if (method == null)
        {
            throw new InvokerException("Management method not found");
        }

        String requestUrl = buildUrl(service.prefix(), method.urlTemplate());

        if (args != null)
        {
            for (Map.Entry<String, String> item : args.entrySet())
            {
                requestUrl = requestUrl.replace(item.getKey(), item.getValue());
            }
        }

        String serviceResponse = getServiceResponse(requestUrl, method.httpMethod(), null);

        Class returnType = managementMethod.getReturnType();
        T result = XmlDeserializer.xmlToObject(serviceResponse, returnType);

        if (result.getClass() != returnType)
        {
            //throw new InvokerException("Invalid response type");
        }

        return result;
    }

    public <T> T invokeManagementMethodPut(Map<String, String> args, Object binary) throws Exception
    {
        final StackTraceElement[] stackElementTrace = Thread.currentThread().getStackTrace();
        String methodName = stackElementTrace[3].getMethodName();
        Class managementClass = Class.forName(stackElementTrace[3].getClassName());

        Method[] methods = managementClass.getMethods();

        Method managementMethod = null;
        for (Method method : methods)
        {
            if (method.getName().equals(methodName))
            {
                managementMethod = method;
                break;
            }
        }

        if (managementMethod == null)
        {
            throw new InvokerException("The method was not found, that is should not be happened");
        }

        ManagementService service = (ManagementService) managementClass.getAnnotation(ManagementService.class);
        if (service == null)
        {
            throw new InvokerException("Management service not found");
        }

        ManagementMethod method = managementMethod.getAnnotation(ManagementMethod.class);
        if (method == null)
        {
            throw new InvokerException("Management method not found");
        }

        String requestUrl = buildUrl(service.prefix(), method.urlTemplate());

        if (args != null)
        {
            for (Map.Entry<String, String> item : args.entrySet())
            {
                requestUrl = requestUrl.replace(item.getKey(), item.getValue());
            }
        }

        String serviceResponse = getServiceResponse(requestUrl, method.httpMethod(), binary);

        Class returnType = managementMethod.getReturnType();
        T result = XmlDeserializer.xmlToObject(serviceResponse, returnType);

        if (result.getClass() != returnType)
        {
            //throw new InvokerException("Invalid response type");
        }

        return result;
    }

    private String buildUrl(String classPrefix, String methodUrlTemplate)
    {
        return String.format(
                "%s/%s/%s",
                urlPrefix,
                classPrefix,
                methodUrlTemplate);
    }

    private String getServiceResponse(String serviceUrl, String httpMethod, Object obj) throws Exception
    {
        String xmlPutParam = null;
        if (obj != null)
        {
            xmlPutParam = XmlSerializer.objectToXml(obj);
        }

        if (httpClient == null)
        {
            httpClient = createHttpClient();
        }

        HttpRequestBase request = null;
        if (httpMethod.equals("PUT"))
        {
            request = new HttpPut(serviceUrl);
            HttpPut put = (HttpPut)request;
            put.setEntity(new StringEntity(xmlPutParam));
        }
        else if (httpMethod.equals("GET"))
        {
            request = new HttpGet(serviceUrl);
        }

        request.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
        HttpResponse httpResponse = httpClient.execute(request);

        if (httpResponse.getStatusLine().getStatusCode() > 399)
        {
            throw new InvokerException(httpResponse.getStatusLine().getReasonPhrase());
        }

        return EntityUtils.toString(httpResponse.getEntity());
    }

    private String getHostIp(String url) throws UnknownHostException
    {
        int start = url.indexOf("//") + 2;
        int end = url.indexOf(":", start);

        String host = url.substring(start, end);

        InetAddress inetAddress = InetAddress.getByName(host);

        return inetAddress.getHostAddress();
    }

    private HttpClient createHttpClient() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, KeyManagementException
    {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);

        org.apache.http.conn.ssl.SSLSocketFactory aasslSocketFactory = new AASSLSocketFactory(keyStore);
        aasslSocketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        HttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, HTTP.UTF_8);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", aasslSocketFactory, 443));
        schemeRegistry.register(new Scheme("https", aasslSocketFactory, 8006/*TODO: */));

        NTCredentials credentials = new NTCredentials(userName, password, getLocalIpAddress(), domain);

        ClientConnectionManager safeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(safeClientConnManager, basicHttpParams);
        defaultHttpClient.getAuthSchemes().register("ntlm", new NTLMSchemeFactory());
        defaultHttpClient.getCredentialsProvider().setCredentials(new AuthScope(getHostIp(urlPrefix), -1), credentials);

        return defaultHttpClient;
    }

    private static void prepareTrustManagement()
    {
        TrustManager[] trustAllCerts = new TrustManager[]
                {
                        new X509TrustManager()
                        {
                            public java.security.cert.X509Certificate[] getAcceptedIssuers()
                            {
                                return null;
                            }

                            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                            {
                            }

                            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                            {
                            }
                        }
                };

        HostnameVerifier verifier = new HostnameVerifier()
        {
            public boolean verify(String string, SSLSession sSLSession)
            {
                return true;
            }
        };

        try
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(verifier);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String getLocalIpAddress()
    {
        String deviceIp = null;
        boolean keepLookupOn = true;

        try
        {
            Enumeration<NetworkInterface> availableNetwork = NetworkInterface.getNetworkInterfaces();

            while (availableNetwork.hasMoreElements() && keepLookupOn)
            {
                NetworkInterface intf = availableNetwork.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();


                while (enumIpAddr.hasMoreElements())
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();

                    deviceIp = inetAddress.getHostAddress();

                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(deviceIp))
                    {
                        keepLookupOn = false;
                        break;
                    }
                }
            }
        }
        catch (SocketException ex)
        {
            ex.printStackTrace();
        }

        return deviceIp;
    }
}
