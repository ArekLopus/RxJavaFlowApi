package subscription;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

//-If request > 4 adds 10
public class Publisher_request {
	
	private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	public Publisher_request() {
		
		IntStream.range(1, 11).forEach(queue::add);
		
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>() {
			
			@Override
			public void subscribe(Subscriber<? super Integer> subscriber) {
				//super.subscribe(subscriber);
				System.out.println("Subsriber's Class" + subscriber.getClass());
				
				subscriber.onSubscribe(new SimpleSubscrition(subscriber));
			}
			
		};
		
		Subscriber_request<Integer> subscriber = new Subscriber_request<>();
		//Subscriber_request2<Integer> subscriber = new Subscriber_request2<>();
		publisher.subscribe(subscriber);
		
		
		publisher.close();
	}
	
	
	private class SimpleSubscrition implements Flow.Subscription {
		
		private Subscriber<? super Integer> subsriber;
		private AtomicBoolean terminated = new AtomicBoolean(false);
		
		public SimpleSubscrition(Subscriber<? super Integer> subscriber) {
			this.subsriber = subscriber;
		}
		
		@Override
		public void request(long n) {
			
			if(n <= 0)
				subsriber.onError(new IllegalArgumentException());
			
			if(queue.isEmpty() && !terminated.getAndSet(true) )
				subsriber.onComplete();
			
			for (int i = 0; i < n && !queue.isEmpty() && !terminated.get() ; i++) {
				if(n > 4) 
					subsriber.onNext(queue.poll() + 10);
				else
					subsriber.onNext(queue.poll());
			}
			
		}

		@Override
		public void cancel() {
			terminated.set(true);
			subsriber.onComplete();
		}
		
	}
	
	public static void main(String[] args) {
		new Publisher_request();
		System.out.println("--- Main Thread Finished ---");
	}
}
