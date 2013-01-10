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
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class PrimeTwinsConsumer implements MessageListener {

    private TopicConnection connection = null;

    private TopicSession session = null;

    private TopicSubscriber subscriber = null;

    public PrimeTwinsConsumer() {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(PrimeConstants.BROKER_URL);
            // Create a Connection
            connection = connectionFactory.createTopicConnection();
            connection.start();

            // Create a Session     
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Topic topicPrimeTwins = session.createTopic(PrimeConstants.TOPIC_PRIME_TWINS);

            subscriber = session.createSubscriber(topicPrimeTwins);
            subscriber.setMessageListener(this);
        } catch (JMSException ex) {
            Logger.getLogger(PrimeTwinsConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            int primeTwinCount = mapMessage.getInt(PrimeConstants.MAP_PRIME_TWIN_COUNT);
            int prime1 = mapMessage.getInt(PrimeConstants.MAP_PRIME1);
            int prime2 = mapMessage.getInt(PrimeConstants.MAP_PRIME2);
            long searchTime = mapMessage.getLong(PrimeConstants.MAP_SEARCH_TIME);

            System.out.println("count=" + primeTwinCount + ";(" + prime1 + ", " + prime2 + ") search time=" + (searchTime / 1000) + "us");
        } catch (JMSException ex) {
            Logger.getLogger(PrimeTwinsConsumer.class.getName()).log(Level.SEVERE, null, ex);
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
