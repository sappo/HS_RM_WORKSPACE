package com.ks.cfxclient;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class PasswordCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[0];
        String id = pwcb.getIdentifier();
        int usage = pwcb.getUsage();
        if (usage == WSPasswordCallback.DECRYPT || usage == WSPasswordCallback.SIGNATURE) {
            // used to retrieve password for private key
            pwcb.setPassword("client");
        }
    }
}
