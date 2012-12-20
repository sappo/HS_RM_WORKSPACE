package com.ks.cfxserver;

import com.ks.cfxinterface.ZKServer;
import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;

/**
 * Implementation of string manipulation server.
 *
 * @author Kevin Sapper (2012)
 */
@WebService(endpointInterface = "com.ks.cfxinterface.ZKServer", serviceName = "zkserver")
public class ZKServerImpl implements ZKServer {

    @Override
    public String verdoppeln(String text, int quantity) {
        return StringUtils.repeat(text, quantity);
    }

    @Override
    public String zeichenweisesVerdoppeln(String text, int quantity) {
        StringBuilder sb = new StringBuilder(text.length() * quantity);
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < quantity; j++) {
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String spiegeln(String text) {
        return StringUtils.reverse(text);
    }

    @Override
    public int laenge(String text) {
        return StringUtils.length(text);
    }

}
