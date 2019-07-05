package simple;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

//-SubmissionPublisher implements the Publisher interface.
public class TestPublisher {

	public TestPublisher() throws InterruptedException {
		
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		
		TestSubscriber<String> subscriber = new TestSubscriber<>();
		
		publisher.subscribe(subscriber);
		
		List<String> items = List.of("1", "2", "3", "4", "5", "3");
		
		items.forEach(publisher::submit);
		
		publisher.submit("One");
		
		
		Thread.sleep(200);
		publisher.close();
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) throws Exception {
		new TestPublisher();

	}

}