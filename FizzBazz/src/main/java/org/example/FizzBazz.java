package org.example;

public class FizzBazz {
    public static byte[] fizz(int value) {
        if (value % 3 == 0 && value % 5 == 0) {
            return "FizzBazz".getBytes();
        }
        if (value % 3 == 0) {
            return "Fizz".getBytes();
        }
        if (value % 5 == 0) {
            return "Bazz".getBytes();
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        testCase(1, "1");
        testCase(3, "Fizz");
        testCase(5, "Bazz");
        testCase(15, "FizzBazz");
        testCase(0, "0");
    }

    private static void testCase(int input, String expected) {
        String result = new String(fizz(input));
        System.out.println("fizz(" + input + ") = '" + result + "' " +
                (result.equals(expected) ? "✓" : "✗ expected: " + expected));
    }
}