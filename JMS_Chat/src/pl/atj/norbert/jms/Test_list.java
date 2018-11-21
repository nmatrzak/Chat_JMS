package pl.atj.norbert.jms;

import java.util.Scanner;
import javax.jms.JMSException;

public class Test_list {
	public static final String URL = "localhost:7676/jms";
	public static final String TOPIC = "ATJTopic";

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		String massege = "";
		System.out.println("Cześć, jak masz na imię?");
		String name = scanner.nextLine();
		System.out.println("Witam: " + name);
		try {
			while (!(massege).equalsIgnoreCase("koniec")) {
				Subscriber con = new Subscriber(Test_list.URL, Test_list.TOPIC);
				con.receiveTopicMessage();
				Publisher publisher = new Publisher(Test_list.URL, Test_list.TOPIC);
				massege = scanner.nextLine();
				publisher.sendQueueMessage(name + " : " + massege);
				publisher.finalize();
				con.finalize();
			}
			scanner.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
