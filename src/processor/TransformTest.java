package processor;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class TransformTest {

	public TransformTest() throws Exception {
		
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
	    TransformProcessor<String, Integer> transformProcessor = new TransformProcessor<>(Integer::parseInt);
	    TransformSubscriber<Integer> subscriber = new TransformSubscriber<>();
	    
	    publisher.subscribe(transformProcessor);
	    transformProcessor.subscribe(subscriber);
	    
	    List<String> items = List.of("1", "2", "3");
	    List<Integer> expectedResult = List.of(1, 2, 3);
	    
	    items.forEach(publisher::submit);
	    publisher.close();
	    
	    Thread.sleep(300);
	    List<Integer> consumedElements = subscriber.consumedElements;
	    
	    System.out.println("Consumed elements: "+subscriber.consumedElements);
	    System.out.println("containsAll(): "+consumedElements.containsAll(expectedResult));
	}
	
	
	public static void main(String[] args) throws Exception {
		new TransformTest();
		System.out.println("--- Main Thread Finished ---");
	}
	
}
