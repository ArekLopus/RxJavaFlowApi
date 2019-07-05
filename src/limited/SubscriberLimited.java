package limited;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicInteger;

public class SubscriberLimited<T> implements Subscriber<T> {
    
	private AtomicInteger howMuchMessagesConsume;
	private Subscription subscription;
    public List<T> consumedElements = new LinkedList<>();
    
    public SubscriberLimited(Integer howMuchMessagesConsume) {
        this.howMuchMessagesConsume = new AtomicInteger(howMuchMessagesConsume);
    }
    
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
		
		int howMuch = howMuchMessagesConsume.decrementAndGet();
		
		System.out.println("Subscriber got: " + item+", thread: "+Thread.currentThread().getName());
	    consumedElements.add(item);
	    
	    if (howMuch > 0) {
            subscription.request(1);
        }
	}

	
}