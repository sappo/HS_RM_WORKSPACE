package com.ks.jmxpub;

import com.google.common.base.Stopwatch;
import com.ks.jmxpub.helper.PrimeConstants;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class PrimeTwinsPublisher implements MessageListener {

    private TopicConnection connection = null;

    private TopicSession session = null;

    private TopicSubscriber subscriber = null;

    private TopicPublisher twinPublisher;

    private int previousPrime;

    private int primeTwinsCount;

    private Stopwatch stopwatch;

    public PrimeTwinsPublisher() {
        this.previousPrime = -1;
        this.previousPrime = 0;
        this.stopwatch = new Stopwatch();
        stopwatch.start();
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(PrimeConstants.BROKER_URL);
            // Create a Connection
            connection = connectionFactory.createTopicConnection();
            connection.start();

            // Create a Session     
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Topic topicPrime = session.createTopic(PrimeConstants.TOPIC_PRIME);
            Topic topicPrimeTwins = session.createTopic(PrimeConstants.TOPIC_PRIME_TWINS);

            subscriber = session.createSubscriber(topicPrime);
            subscriber.setMessageListener(this);

            twinPublisher = session.createPublisher(topicPrimeTwins);
        } catch (JMSException ex) {
            Logger.getLogger(PrimeTwinsPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            int primeCount = mapMessage.getInt(PrimeConstants.MAP_PRIME_COUNT);
            int prime = mapMessage.getInt(PrimeConstants.MAP_PRIME);
            long searchTime = mapMessage.getLong(PrimeConstants.MAP_SEARCH_TIME);

            if ((prime - previousPrime) == 2) {
                stopwatch.stop();
                MapMessage twinMessage = session.createMapMessage();
                twinMessage.setInt(PrimeConstants.MAP_PRIME_TWIN_COUNT, primeTwinsCount);
                twinMessage.setInt(PrimeConstants.MAP_PRIME1, previousPrime);
                twinMessage.setInt(PrimeConstants.MAP_PRIME2, prime);
                twinMessage.setLong(PrimeConstants.MAP_SEARCH_TIME, stopwatch.elapsedTime(TimeUnit.NANOSECONDS));
                twinPublisher.send(twinMessage);
                primeTwinsCount++;
                stopwatch.reset();
                stopwatch.start();
            }
            previousPrime = prime;
//            System.out.println("prime count=" + primeCount + ";prime=" + prime + ";search time=" + (searchTime / 1000) + "us");
        } catch (JMSException ex) {
            Logger.getLogger(PrimeTwinsPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        // Clean up
        twinPublisher.close();
        subscriber.close();
        session.close();
        connection.close();
    }
}
