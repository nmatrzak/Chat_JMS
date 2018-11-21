package pl.atj.norbert.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Topic;

public class Subscriber {

	private JMSContext jmsContext;
	private JMSConsumer jmsConsumer;
	private Topic topic;

	Subscriber(String url, String topicName) throws JMSException {
		ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		jmsContext = connectionFactory.createContext();
		((com.sun.messaging.ConnectionFactory) connectionFactory)
				.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, url);
		topic = new com.sun.messaging.Topic(topicName); // "ATJTopic"
		jmsConsumer = jmsContext.createConsumer(topic);
	}

	public void receiveTopicMessage() {
		jmsConsumer.setMessageListener(new Listener());
	}

	protected void finalize() {
		if (jmsConsumer != null)
			jmsConsumer.close();
		if (jmsContext != null)
			jmsContext.close();
	}

}
