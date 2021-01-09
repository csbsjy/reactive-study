package reactive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleCellTest {

    @DisplayName("c1, c2를 구독하는 c3는 c1,c2의 값이 변경될 때마다 그 사실을 안다")
    @Test
    void simple() {
        SimpleCell<Integer> c3 = new SimpleCell<>("c3");
        SimpleCell<Integer> c2 = new SimpleCell<>("c2");
        SimpleCell<Integer> c1 = new SimpleCell<>("c1");

        c1.subscribe(c3);
        c2.subscribe(c3);

        c1.onNext(10);
        c1.onNext(20);
        c2.onNext(10);
        c2.onNext(20);
    }

}