package subscription;

//	Flow.Subscription
//-Flow.Subscription is a message control between Flow.Publisher and Flow.Subscriber.
//-A subscriber receives a message only when requested and may cancel subscription at any time.
//-The methods of Flow.Subscription are invoked only by their subscribers. Find the methods of Flow.Subscription. 

//	void request(long n)
//-On calling request method, given n numbers of items are requested by subscriber. n can be 1,2, ..n. 
// When we pass –ve number, exception is thrown and onError method of Flow.Subscriber is invoked. 

//	void cancel()
// cancel method cancels the subscription of subscriber and stops receiving messages.
// A canceled subscription will not invoke onComplete or onError method of Flow.Subscriber.

public class AnInfo {}