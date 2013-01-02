package com.ks.cfxserver;

import com.ks.cfxinterface.ZKServer;
import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class CfxServerMain {

    public static void main(String[] args) {
        // create new web server
        System.out.println("Starting Server");
        ZKServerImpl implementor = new ZKServerImpl();
        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(ZKServer.class);
        svrFactory.setAddress("http://localhost:9000/zkserver");
        svrFactory.setServiceBean(implementor);
        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        Server server = svrFactory.create();

        Endpoint cxfEndpoint = server.getEndpoint();

        // verify signature and decrypt message
        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION,
                WSHandlerConstants.TIMESTAMP + " "
                + WSHandlerConstants.SIGNATURE + " "
                + WSHandlerConstants.ENCRYPT);
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallbackHandler.class.getName());
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "server.properties");
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "server.properties");

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
        cxfEndpoint.getInInterceptors().add(wssIn);

        // sign and encrypte message
        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.ACTION,
                WSHandlerConstants.TIMESTAMP + " "
                + WSHandlerConstants.SIGNATURE + " "
                + WSHandlerConstants.ENCRYPT);
        outProps.put(WSHandlerConstants.USER, "server");
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, "useReqSigCert"); // dynamically find certificate for encryption
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallbackHandler.class.getName());
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, "server_sign.properties");
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "server_sign.properties");
        // only encrypte the content of the result
        outProps.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Content}{http://cfxinterface.ks.com/params}result");

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        cxfEndpoint.getOutInterceptors().add(wssOut);
    }
}
