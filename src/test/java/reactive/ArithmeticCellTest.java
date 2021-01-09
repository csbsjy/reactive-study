package reactive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticCellTest {

    @DisplayName("c1, c2 를 구독하는 덧셈셀 c2는 두 값이 변경될 때마다 그 합을 출력한다")
    @Test
    void add() {
        ArithmeticCell c3 = new ArithmeticCell("c3");
        SimpleCell<Integer> c2 = new SimpleCell<>("c2");
        SimpleCell<Integer> c1 = new SimpleCell<>("c1");

        c2.subscribe(c3::setLeft);
        c1.subscribe(c3::setRight);

        c2.onNext(10);
        c1.onNext(20);
    }

}