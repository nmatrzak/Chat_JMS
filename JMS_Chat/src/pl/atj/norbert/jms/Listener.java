package pl.atj.norbert.jms;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener{

	@Override
	public void onMessage(Message msg) {
		if (msg instanceof TextMessage) {
			try {
				String message = ((TextMessage) msg).getText();
				System.out.println(message);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
	}
}