package simple;

import java.util.Arrays;

import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

//Interned strings, locks, and more
//-The JVM interns String literals in a memory pool of String objects; there's a single copy of each interned String object in memory.
// Fe, each "A" literal identifies the single interned String object containing A.
//-The synchronization code obtains the lock associated with the single interned "A" object.
public class AFlowDemo {
	
	public static void main(String[] args) {
		
		// Create a publisher.
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

		// Create a subscriber and register it with the publisher.
		ASubscriber<String> subscriber = new ASubscriber<>();
		publisher.subscribe(subscriber);

		// Publish several data items and then close the publisher.
		System.out.println("Publishing data items...");
		String[] items = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };
		Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
		publisher.close();

		try {
			synchronized ("A") {
				"A".wait();
			}
		} catch (InterruptedException ie) {}
	}
}

class ASubscriber<T> implements Subscriber<T> {
	
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(T item) {
		System.out.println("Received: " + item);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		t.printStackTrace();
		synchronized ("A") {
			"A".notifyAll();
		}
	}

	@Override
	public void onComplete() {
		System.out.println("Done");
		synchronized ("A") {
			"A".notifyAll();
		}
	}
}