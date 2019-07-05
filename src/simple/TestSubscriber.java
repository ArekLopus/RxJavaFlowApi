package simple;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TestSubscriber<T> implements Subscriber<T> {
    private Subscription subscription;
 
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

	@Override
	public void onNext(T item) {
		System.out.println("Received: " + item + ", thread: " + Thread.currentThread().getName());
	    subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println("Done");
	}
	
}