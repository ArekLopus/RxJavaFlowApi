package simple;

import java.util.concurrent.SubmissionPublisher;

@SuppressWarnings("unchecked")
public class PublisherTestMultiSubs {

	public PublisherTestMultiSubs() throws InterruptedException {
		
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		
		TestSubscriber<String>[] subscribers = new TestSubscriber[6];
		
		for (int i = 0; i < subscribers.length; i++) {
			subscribers[i] = new TestSubscriber<>();
			publisher.subscribe(subscribers[i]);
		}
		
		
		publisher.submit("One");
		
		
		Thread.sleep(200);
		publisher.close();
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) throws Exception {
		new PublisherTestMultiSubs();

	}

}