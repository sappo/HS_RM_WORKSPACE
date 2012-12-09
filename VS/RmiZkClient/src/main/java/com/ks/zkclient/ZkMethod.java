package com.ks.zkclient;

/**
 *
 * @author Kevin Sapper (2012)
 */
public enum ZkMethod {

    DUP_WORD("verdoppeln"), DUP_CHAR("zeichenweises verdoppeln"), MIRROR("spiegeln"), LENGTH("länge");

    private String option;

    private ZkMethod(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return option;
    }

}
