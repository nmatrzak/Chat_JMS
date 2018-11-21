package pl.atj.norbert.jms;


import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;

import com.sun.messaging.Topic;

public class Publisher {
	private JMSContext jmsContext;
	private JMSProducer jmsProducer;
	private Topic topic;

	Publisher(String url, String topicName) throws JMSException {
		ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();

		jmsContext = connectionFactory.createContext();
		((com.sun.messaging.ConnectionFactory) connectionFactory)
				.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, url);
		jmsProducer = jmsContext.createProducer();
		topic = new com.sun.messaging.Topic(topicName);
	}

	public void sendQueueMessage(String msg) {
		jmsProducer.send(topic, msg);
	}
	
	protected void finalize(){
		if (jmsContext != null) jmsContext.close();
	}
}

