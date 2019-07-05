package subscription;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class Subscriber_cancel<T> implements Subscriber<T> {
    
	private int counter = 0;
	private int tenCounter = 0;
	
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
		counter++;
		
		if((Integer)item == 10) {
			tenCounter++;
			System.out.println("Subscriber got 10, counter: " + counter+ ", tenCounter: "+ tenCounter +", thread: "+Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		subscription.request(1);
	}

	public int getTenCounter() {
		return tenCounter;
	}

	public Subscription getSubscription() {
		return subscription;
	}
	
}