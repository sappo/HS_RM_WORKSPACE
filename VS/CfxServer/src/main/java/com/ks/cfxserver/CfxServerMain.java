package com.ks.cfxserver;

import com.ks.cfxinterface.ZKServer;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class CfxServerMain {

    public static void main(String[] args) {
        System.out.println("Starting Server");
        ZKServerImpl implementor = new ZKServerImpl();
        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(ZKServer.class);
        svrFactory.setAddress("http://localhost:9000/zkserver");
        svrFactory.setServiceBean(implementor);
        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        svrFactory.create();
    }
}
