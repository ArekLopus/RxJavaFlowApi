package processor;

//-Flow.Processor acts as both a subscriber and publisher.
//-It inherits methods of Flow.Subscriber and Flow.Publisher interfaces.
// So Flow.Processor contains subscribe, onComplete, onError, onNext, onSubscribe methods. 

//-Flow.Processor is used for object transformation. Suppose producer produces object A and subscriber will receive object B
// then processor will transform object A to object B. Subscriber will subscribe to processor and processor will subscribe to producer.
//-The items produced by publisher, will be received by processor and then processor will produce that items to be received by subscriber.

public class AnInfo {}
