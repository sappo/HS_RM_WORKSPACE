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
    @WebResult(name = "result")
    public String verdoppeln(@WebParam(name = "text") String text, @WebParam(name = "quantity") int quantity);

    @ServerMethod
    @WebMethod
    @WebResult(name = "result")
    public String zeichenweisesVerdoppeln(@WebParam(name = "text") String text, @WebParam(name = "quantity") int quantity);

    @ServerMethod
    @WebMethod
    @WebResult(name = "result")
    public String spiegeln(@WebParam(name = "text") String text);

    @ServerMethod
    @WebMethod
    @WebResult(name = "result")
    public int laenge(@WebParam(name = "text") String text);
}
