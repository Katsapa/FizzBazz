import org.example.FizzBazz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {

    @Test
    public void fizzBazzTest1() {
        assertArrayEquals("Fizz".getBytes(), FizzBazz.fizz(6));
        assertArrayEquals("Fizz".getBytes(), FizzBazz.fizz(3));
        assertArrayEquals("Fizz".getBytes(), FizzBazz.fizz(9));
    }

    @Test
    public void fizzBazzTest2() {
        assertArrayEquals("Bazz".getBytes(), FizzBazz.fizz(10));
        assertArrayEquals("Bazz".getBytes(), FizzBazz.fizz(5));
        assertArrayEquals("Bazz".getBytes(), FizzBazz.fizz(20));
    }

    @Test
    public void fizzBazzTest3() {
        assertArrayEquals("FizzBazz".getBytes(), FizzBazz.fizz(15));
        assertArrayEquals("FizzBazz".getBytes(), FizzBazz.fizz(30));
        assertArrayEquals("FizzBazz".getBytes(), FizzBazz.fizz(45));
    }

    @Test
    public void fizzBazzTest4() {
        assertArrayEquals("1".getBytes(), FizzBazz.fizz(1));
        assertArrayEquals("2".getBytes(), FizzBazz.fizz(2));
        assertArrayEquals("4".getBytes(), FizzBazz.fizz(4));
        assertArrayEquals("7".getBytes(), FizzBazz.fizz(7));
    }

    @Test
    public void fizzBazzTest5() {
        assertThrows(IllegalArgumentException.class, () -> {
            FizzBazz.fizz(2);
        });
    }
}
