package subscription;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class Subscriber_request2<T> implements Subscriber<T> {
    
	private Subscription subscription;
	
    @Override
	public void onSubscribe(Subscription s) {
		subscription = s;
		subscription.request(1);
	}
    
	@Override
	public void onComplete() {
		System.out.println("Completed.");
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("Error: "+t.getMessage());
	}

	@Override
	public void onNext(T item) {
		System.out.println("Item: " + item);
		subscription.request(1);
	}
	
	
	public Subscription getSubscription() {
		return subscription;
	}
	
}