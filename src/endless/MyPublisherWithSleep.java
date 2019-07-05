package endless;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.ThreadLocalRandom;

public class MyPublisherWithSleep {
	
	public MyPublisherWithSleep() {
				
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
		MySubscriberWithSleep<Integer> subscriber = new MySubscriberWithSleep<>();
		publisher.subscribe(subscriber);
		
		while(true) {
			int nextInt = (ThreadLocalRandom.current().nextInt(10))+1;
			publisher.submit(nextInt);
			
			if(subscriber.getTenCounter() == 10) {
				subscriber.getSubscription().cancel();
				break;
			}
				
		}
		
		publisher.close();
	}
	
	
	public static void main(String[] args) {
		new MyPublisherWithSleep();
		System.out.println("--- Main Thread Finished ---");
	}
}
