package simple.custom_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

//-SubmissionPublisher implements the Publisher interface.
public class PublisherTest {

	public PublisherTest() throws InterruptedException {
		
		ExecutorService es = Executors.newFixedThreadPool(4);
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>(es, 1024);
		
		SubscriberTest<String> subscriber = new SubscriberTest<>();
		SubscriberTest<String> subscriber2 = new SubscriberTest<>();
		
		publisher.subscribe(subscriber);
		publisher.subscribe(subscriber2);
		

		publisher.submit("One");
		publisher.submit("Two");
		publisher.submit("Three");
		
		
		Thread.sleep(300);
		publisher.close();
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) throws Exception {
		new PublisherTest();

	}

}