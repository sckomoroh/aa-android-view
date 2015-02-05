package com.communicator.ntlm;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.impl.auth.NTLMScheme;
import org.apache.http.params.HttpParams;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/11/13
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class NTLMSchemeFactory implements AuthSchemeFactory
{
    public AuthScheme newInstance(HttpParams params)
    {
        return new NTLMScheme(new JCIFSEngine());
    }
}
