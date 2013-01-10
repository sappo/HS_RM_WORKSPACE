package com.ks.jmxpub;

import javax.jms.JMSException;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, JMSException {
        // create consumer
        PrimeTwinsPublisher twinPublisher = new PrimeTwinsPublisher();
        PrimeTwinsConsumer twinConsumer = new PrimeTwinsConsumer();
        // create publisher
        Runnable publisher = new PrimePublisher();
        Thread thread = new Thread(publisher);
        thread.start();

//        while (thread.isAlive()) {
//            Thread.sleep(1000);
//        }
//        System.exit(0);
    }
}   
