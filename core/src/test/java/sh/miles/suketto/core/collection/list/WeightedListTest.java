package sh.miles.suketto.core.collection.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class WeightedListTest {

    private static final Field totalField;

    static {
        try {
            totalField = WeightedList.class.getDeclaredField("total");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private WeightedList<String> list;

    @BeforeEach
    public void setup() {
        list = new WeightedList<>();
        list.add(.5, "a");
        list.add(.25, "b");
        list.add(.25, "c");
        list.add(.3, "d");
        list.add(.2, "e");
    }

    @Test
    public void testListAdd() {
        final WeightedList<String> test = new WeightedList<>();
        test.add(.5, "a");
        assertEquals(.5, getTotal(test), "total should be .5");
        test.add(.25, "b");
        assertEquals(.75, getTotal(test), "total should be .75");
        test.add(.25, "c");
        assertEquals(1, getTotal(test), "total should be .1");
        test.add(.3, "d");
        assertEquals(1.3, getTotal(test), "total should be 1.3");
        test.add(.2, "e");
        assertEquals(1.5, getTotal(test), "total should be 1.5");
    }

    @RepeatedTest(10)
    public void testDraw() {
        assertNotNull(list.next(), "next should never be null");
    }

    private static double getTotal(WeightedList<?> weightedList) {
        final double total;
        try {
            totalField.setAccessible(true);
            total = (Double) totalField.get(weightedList);
            totalField.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
}
