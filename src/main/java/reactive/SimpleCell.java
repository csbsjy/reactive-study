package reactive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

/**
 * 값이 갱신될 때마다 구독자 셀에 전파하는 Cell
 * SimpleCell은 전파자(Publisher)이자 구독자(Subcribber)가된다
 */
public class SimpleCell<T> implements Publisher<T>, Subscriber<T> {
    private final String name;
    private T value;
    private final List<Subscriber<? super T>> subscribers = new ArrayList<>();

    public SimpleCell(final String name) {
        this.name = name;
    }

    /**
     * 값이 갱신될 때마다 알릴 구독자 목록에 추가한다
     *
     * @param subscriber
     */
    @Override
    public void subscribe(final Subscriber<? super T> subscriber) {
        this.subscribers.add(subscriber);
    }

    public void subscribe(final Consumer<? super T> consumer) {
        this.subscribers.add(new Subscriber<>() {
            @Override
            public void onNext(final T item) {
                consumer.accept(item);
            }

            @Override
            public void onSubscribe(final Subscription subscription) {

            }

            @Override
            public void onError(final Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 값이 갱신되었다. 구독자에게 알려야 함.
     *
     * @param item
     */
    @Override
    public void onNext(final T item) {
        this.value = item;
        System.out.printf("%s: %s%n", this.name, item.toString());
        this.subscribers.forEach(
                subscriber -> subscriber.onNext(item) // 값이 갱신되었음을 알린다
        );
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(final Throwable throwable) {

    }

    @Override
    public void onSubscribe(final Subscription subscription) {
    }
}
