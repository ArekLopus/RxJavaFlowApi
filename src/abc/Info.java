package abc;

//	Reactive Streams support in the JDK
//-Starting from version 9, the Reactive Streams interfaces — formerly available as a separate library —
// have become a part of the JDK in the java.util.concurrent.Flow class.

//-The Java 9 Flow API was created as an integration point for existing async libraries, not as an invitation to implement reactive
// components in an ad-hoc fashion. It's great to experiment with, but if you really want to create a reactive system,
// the existing libraries are likely well-suited.


//-The four interfaces seem fairly simple at first sight:
// • Publisher<T> is responsible for publishing elements of type T and provides a subscribe method for subscribers to connect to it,
// • Subscriber<T> connects to a Publisher, receives a confirmation via onSubscribe, then receive data via the onNext callbacks
//   and additional signals via onError and onComplete,
// • Subscription represents a link between a Publisher and a Subscriber, and allows for backpressuring the publisher with request
//   or terminating the link with cancel,
// • Processor combines the capabilities of a Publisher and a Subscriber in a single interface


//	Flow.Subscriber
//-Flow.Subscriber is the receiver of messages. It has following methods. 
//	void onSubscribe(Flow.Subscription subscription)
//-onSubscribe method is invoked to enable subscriber to receive messages. Out of all methods of Flow.Subscriber, this method is invoked first.
//-If there is error at this step, then resulting behavior is not certain, possible that subscription will not be established or to be cancelled. Inside this method we usually call request method of Flow.Subscription. 
//	void onNext(T item)
//-onNext method is invoked for next item of Flow.Subscription. If this method throws an error, possibly subscription will be canceled. 
//	void onError(Throwable throwable)
//-onError method is invoked when Flow.Publisher or Flow.Subscriber throws unrecoverable error.
//-After getting error, no more method of Flow.Subscriber will be executed by Flow.Subscription.
//-If onError method throws exception, then resulting behavior is undefined. 
//	void onComplete()
//-onComplete method is invoked when no invocation of Flow.Subscriber method is left for a Flow.Subscription that is not already terminated
//by an error. For any Flow.Subscription error, no more Flow.Subscriber method will run and this will not be the case of onComplete
// method invocation. If onComplete method throws error, the resulting behavior is undefined.

//	Flow.Publisher
//-Flow.Publisher produces items to be received by subscribers.
//-Each subscriber receives the same item in same order using onNext() of Flow.Subscriber unless no error occurred or subscription is cancelled.
//-When Flow.Publisher throws error, it will be received by onError of Flow.Subscriber and no other message will be received by Flow.Subscriber.
//-When Flow.Publisher completes producing message normally, the onComplete method of Flow.Subscriber will be invoked and then no other
// message will be received by subscriber. 
//-Flow.Subscriber has following method.
//	void subscribe(Flow.Subscriber<? super T> subscriber)
//-subscribe method adds the given subscriber to the calling publisher.
//-On successful subscription, the onSubscribe method of Flow.Subscriber is invoked.
//-If there is an error to execute subscribe method, the onError method of Flow.Subscriber will be invoked.

//	Flow.Subscription
//-Flow.Subscription is a message control between Flow.Publisher and Flow.Subscriber.
//-A subscriber receives a message only when requested and may cancel subscription at any time.
//-The methods of Flow.Subscription are invoked only by their subscribers. Find the methods of Flow.Subscription. 
//	void request(long n)
//-On calling request method, given n numbers of items are requested by subscriber. n can be 1,2, ..n. 
// When we pass negative number, exception is thrown and onError method of Flow.Subscriber is invoked. 
//	void cancel()
//-cancel method cancels the subscription of subscriber and stops receiving messages.
//-A canceled subscription will not invoke onComplete or onError method of Flow.Subscriber.

//	Flow.Processor
//-Flow.Processor acts as both a subscriber and publisher.
//-It inherits methods of Flow.Subscriber and Flow.Publisher interfaces.
// So Flow.Processor contains subscribe, onComplete, onError, onNext, onSubscribe methods. 
//-Flow.Processor is used for object transformation. Suppose producer produces object A and subscriber will receive object B
// then processor will transform object A to object B. Subscriber will subscribe to processor and processor will subscribe to producer.
//-The items produced by publisher, will be received by processor and then processor will produce that items to be received by subscriber.


//	SubmissionPublisher
//-SubmissionPublisher is the implementation of Flow.Publisher interface. It provides submitted items to subscriber.
//-Once it is closed then no item will be delivered.
//-All subscribers receive items in the same order as produced by producer.
//-SubmissionPublisher is a compliant reactive-streams publisher.
//-By default SubmissionPublisher uses ForkJoinPool.commonPool() for async delivery to subscriber.
//-We can use Executor by passing as constructor argument while creating object of SubmissionPublisher.

public class Info {}