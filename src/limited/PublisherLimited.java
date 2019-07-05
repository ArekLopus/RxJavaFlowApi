package limited;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class PublisherLimited {
	
	public PublisherLimited() {
		
		List<String> items = List.of("1", "2", "3", "4", "5", "3");
				
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		
		//MySubscriberLimited<String> subscriber = new MySubscriberLimited<>(1);
		SubscriberLimited<String> subscriber = new SubscriberLimited<>(3);
		publisher.subscribe(subscriber);
		
		
		items.forEach(publisher::submit);
		publisher.close();
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		new PublisherLimited();
		
		Thread.sleep(200);
		System.out.println("--- Main Thread Finished ---");
	}
}
