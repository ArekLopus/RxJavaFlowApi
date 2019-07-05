package processor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TransformSubscriber<T> implements Subscriber<T> {
    private Subscription subscription;
    public List<T> consumedElements = new LinkedList<>();
 
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

	@Override
	public void onNext(T item) {
		subscription.request(1);
		System.out.println("Received: " + item + ", thread: " + Thread.currentThread().getName());
		consumedElements.add(item);
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println("Subscriber Done");
	}
	
}