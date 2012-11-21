package com.ks.zkinterface;

/**
 * Interface for string manipulation.
 * @author Kevin Sapper (2012)
 */
public interface ZKServer {
    public String verdoppeln(String text, int quantity);
    public String zeichenweisesVerdoppeln(String text, int quantity);
    public String spiegeln(String text);
    public int laenge(String text);
}
