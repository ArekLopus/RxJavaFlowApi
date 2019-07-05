package processor;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class SimpleProcessor extends SubmissionPublisher<Integer> implements Processor<String, Integer> {
	
	private Subscription subscription;
	private Function<String, Integer> function;
	
	public SimpleProcessor(Function<String, Integer> fun) {
		this.function = fun;
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		System.out.println("Processor Subscribed");
		this.subscription = subscription;
		subscription.request(1);
	}
	
	@Override
	public void onNext(String item) {
		subscription.request(1);
		submit(function.apply(item));
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println(throwable.getMessage());
		
	}

	@Override
	public void onComplete() {
		System.out.println("Processor Done");
	}

	@Override
	public void subscribe(Subscriber<? super Integer> subscriber) {
		super.subscribe(subscriber);
		System.out.println("Processor, Subscribed: " + subscriber.getClass());
	}
	
	
	
}
