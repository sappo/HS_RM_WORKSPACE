package com.ks.jmxpub;

import com.google.common.base.Stopwatch;
import com.ks.jmxpub.helper.PrimeConstants;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class PrimePublisher implements Runnable {

    private static int MAX_PRIME = 1_000_000;

    private TopicConnection connection = null;

    private TopicSession session = null;

    public void run() {
        int primesFound = 0;
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(PrimeConstants.BROKER_URL);

            // Create a Connection
            connection = connectionFactory.createTopicConnection();
            connection.start();

            // Create a Session     
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Topic topic = session.createTopic(PrimeConstants.TOPIC_PRIME);

            TopicPublisher publisher = session.createPublisher(topic);
            
            Stopwatch stopwatch = new Stopwatch();
            for (int i = 3; i < MAX_PRIME; i += 2) {
                stopwatch.start();
                boolean isPrime = true;
                for (int j = 3; j < i; j += 2) {
                    if (i % j == 0) {
                        isPrime = false;
                    }
                }
                stopwatch.stop();
                long time = stopwatch.elapsedTime(TimeUnit.NANOSECONDS);
                if (isPrime) {
                    MapMessage mapMessage = session.createMapMessage();
                    mapMessage.setInt(PrimeConstants.MAP_PRIME_COUNT, primesFound++);
                    mapMessage.setInt(PrimeConstants.MAP_PRIME, i);
                    mapMessage.setLong(PrimeConstants.MAP_SEARCH_TIME, time);
                    publisher.send(mapMessage);
                }
                stopwatch.reset();
            }
        } catch (JMSException ex) {
            Logger.getLogger(PrimePublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        // Clean up
        session.close();
        connection.close();
    }
}
