package com.apprecovery.config;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class AACoreConfig
{
    private static AACoreConfig ourInstance = new AACoreConfig();

    private String userName;
    private String domain;
    private String password;
    private String host;

    public static AACoreConfig getInstance()
    {
        return ourInstance;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        int index = userName.indexOf("\\");

        if (index == -1)
        {
            this.userName = userName;
            this.domain = "";
        }
        else
        {
            this.userName = userName.substring(index + 1);
            this.domain = userName.substring(0, index);
        }
    }

    public String getDomain()
    {
        return this.domain;
    }

    public String getServiceUrl()
    {
        return String.format("https://%s:8006/apprecovery/api/core", this.host);
    }

    private AACoreConfig()
    {
    }
}
