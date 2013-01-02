package com.ks.cfxinterface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Interface for string manipulation.
 *
 * @author Kevin Sapper (2012)
 */
@WebService
public interface ZKServer {

    @ServerMethod
    @WebMethod
    @WebResult(name = "result", partName = "result", targetNamespace = "http://cfxinterface.ks.com/params")
    public String verdoppeln(@WebParam(name = "text", partName = "text", targetNamespace = "http://cfxinterface.ks.com/params") String text, @WebParam(name = "quantity") int quantity);

    @ServerMethod
    @WebMethod
    @WebResult(name = "result", partName = "result", targetNamespace = "http://cfxinterface.ks.com/params")
    public String zeichenweisesVerdoppeln(@WebParam(name = "text", partName = "text", targetNamespace = "http://cfxinterface.ks.com/params") String text, @WebParam(name = "quantity") int quantity);

    @ServerMethod
    @WebMethod
    @WebResult(name = "result", partName = "result", targetNamespace = "http://cfxinterface.ks.com/params")
    public String spiegeln(@WebParam(name = "text", partName = "text", targetNamespace = "http://cfxinterface.ks.com/params") String text);

    @ServerMethod
    @WebMethod
    @WebResult(name = "result", partName = "result", targetNamespace = "http://cfxinterface.ks.com/params")
    public int laenge(@WebParam(name = "text", partName = "text", targetNamespace = "http://cfxinterface.ks.com/params") String text);
}
