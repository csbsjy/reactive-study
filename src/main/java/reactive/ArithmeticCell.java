package reactive;

import java.util.concurrent.Flow.*;

/**
 * 두 셀의 값이 변경될 때마다 그 합을 가지는 Cell
 * 즉, 두 셀을 구독해야한다.
 */
public class ArithmeticCell implements Subscriber<Integer> {
    private int value;
    private int left;
    private int right;
    private String name;

    public ArithmeticCell(final String name) {
        this.name = name;
    }

    public void setLeft(final int left) {
        this.left = left;
        onNext(this.left + right);
    }

    public void setRight(final int right) {
        this.right = right;
        onNext(this.left + right);
    }

    @Override
    public void onSubscribe(final Subscription subscription) {

    }

    @Override
    public void onNext(final Integer item) {
        this.value = item;
        System.out.printf("%s: %d%n", this.name, this.value);
    }

    @Override
    public void onError(final Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
