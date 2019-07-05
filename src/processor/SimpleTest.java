package processor;

import java.util.concurrent.SubmissionPublisher;

public class SimpleTest {

	public SimpleTest() throws Exception {
		
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		SimpleProcessor processor = new SimpleProcessor(str -> str.length());
		TransformSubscriber<Integer> subscriber = new TransformSubscriber<>();
		
		publisher.subscribe(processor);
		processor.subscribe(subscriber);
		
		processor.submit(123);
		
		publisher.submit("One");
		publisher.submit("Three");
		publisher.submit("Five");
		
		
		Thread.sleep(200);
		
		publisher.close();
		processor.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		new SimpleTest();
		System.out.println("--- Main Thread Finished ---");
	}
	
}
