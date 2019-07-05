package simple;

import java.util.concurrent.SubmissionPublisher;

//-SubmissionPublisher implements the Publisher interface.
public class PublisherTestMethods {

	public PublisherTestMethods() throws InterruptedException {
		
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		TestSubscriber<String> subscriber = new TestSubscriber<>();
		publisher.subscribe(subscriber);
		
		publisher.consume(i -> System.out.println(i + ", thread" + Thread.currentThread().getName()));
		publisher.submit("One");
		
		System.out.println("Buffer size: " + publisher.getMaxBufferCapacity());
		
		publisher.offer("Offer", (s, str) -> !str.equals(null));
		
		
		Thread.sleep(600);
		
		publisher.close();
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) throws Exception {
		new PublisherTestMethods();

	}

}