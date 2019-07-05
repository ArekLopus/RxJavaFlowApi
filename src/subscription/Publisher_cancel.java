package subscription;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.ThreadLocalRandom;

public class Publisher_cancel {
	
	public Publisher_cancel() {
				
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
		Subscriber_cancel<Integer> subscriber = new Subscriber_cancel<>();
		publisher.subscribe(subscriber);
		
		while(true) {
			int nextInt = (ThreadLocalRandom.current().nextInt(10))+1;
			publisher.submit(nextInt);
			
			if(subscriber.getTenCounter() == 5) {
				subscriber.getSubscription().cancel();
				break;
			}
		}
		
		publisher.close();
	}
	
	
	public static void main(String[] args) {
		new Publisher_cancel();
		System.out.println("--- Main Thread Finished ---");
	}
}
