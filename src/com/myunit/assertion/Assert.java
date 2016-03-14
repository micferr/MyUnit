package com.myunit.assertion;

import java.util.Comparator;
import java.util.Objects;

public class Assert {

    public static void fail() {
        throw new AssertionError();
    }

    public static void fail(String message) {
        throw new AssertionError(message);
    }

    // Assert True / False

    public static void assertTrue(boolean expression, String message) {
        if (!expression) {
            if (message != null) {
                fail(message);
            } else {
                fail();
            }
        }
    }

    public static void assertTrue(boolean expression) {
        assertTrue(expression, null);
    }

    public static void assertFalse(boolean expression, String message) {
        assertTrue(!expression, message);
    }

    public static void assertFalse(boolean expression) {
        assertFalse(expression, null);
    }

    static void assertEquals(Object actual, Object expected, String message) {
        assertTrue(Objects.equals(actual, expected), message);
    }

    // Assert Equals / NotEquals

    public static void assertEquals(Object actual, Object expected) {
        assertEquals(actual, expected, null);
    }

    public static void assertEquals(byte actual, byte expected, String message) {
        assertTrue(actual == expected, message);
    }

    public static void assertEquals(byte actual, byte expected) {
        assertEquals(actual, expected, null);
    }

    public static void assertEquals(short actual, short expected, String message) {
        assertTrue(actual == expected, message);
    }

    public static void assertEquals(short actual, short expected) {
        assertEquals(actual, expected, null);
    }

    public static void assertEquals(int actual, int expected, String message) {
        assertTrue(actual == expected, message);
    }

    public static void assertEquals(int actual, int expected) {
        assertEquals(actual, expected, null);
    }

    public static void assertEquals(long actual, long expected, String message) {
        assertTrue(actual == expected, message);
    }

    public static void assertEquals(long actual, long expected) {
        assertEquals(actual, expected, null);
    }

    public static void assertEquals(float actual, float expected, String message) {
        fail("Float equality not a good test. Use assertEquals(float,float,float[,String]) instead");
    }

    public static void assertEquals(float actual, float expected) {
        assertEquals(actual, expected, null);
    }

    public static void assertEquals(float actual, float expected, float tolerance, String message) {
        assertTrue(Math.abs(actual - expected) <= tolerance, message);
    }

    public static void assertEquals(float actual, float expected, float tolerance) {
        assertEquals(actual, expected, tolerance, null);
    }

    public static void assertEquals(double actual, double expected, String message) {
        fail("Double equality not a good test. Use assertEquals(double,double,double[,String]) instead");
    }

    public static void assertEquals(double actual, double expected) {
        assertEquals(actual, expected, null);
    }

    public static void assertEquals(double actual, double expected, double tolerance, String message) {
        assertTrue(Math.abs(actual - expected) <= tolerance, message);
    }

    public static void assertEquals(double actual, double expected, double tolerance) {
        assertEquals(actual, expected, tolerance, null);
    }

    public static void assertEquals(char actual, char expected, String message) {
        assertTrue(actual == expected, message);
    }

    public static void assertEquals(char actual, char expected) {
        assertEquals(actual, expected, null);
    }

    public static void assertNotEquals(Object actual, Object expected, String message) {
        assertFalse(Objects.equals(actual, expected), message);
    }

    public static void assertNotEquals(Object actual, Object expected) {
        assertNotEquals(actual, expected, null);
    }

    public static void assertNotEquals(byte actual, byte expected, String message) {
        assertFalse(actual == expected, message);
    }

    public static void assertNotEquals(byte actual, byte expected) {
        assertNotEquals(actual, expected, null);
    }

    public static void assertNotEquals(short actual, short expected, String message) {
        assertFalse(actual == expected, message);
    }

    public static void assertNotEquals(short actual, short expected) {
        assertNotEquals(actual, expected, null);
    }

    public static void assertNotEquals(int actual, int expected, String message) {
        assertFalse(actual == expected, message);
    }

    public static void assertNotEquals(int actual, int expected) {
        assertNotEquals(actual, expected, null);
    }

    public static void assertNotEquals(long actual, long expected, String message) {
        assertFalse(actual == expected, message);
    }

    public static void assertNotEquals(long actual, long expected) {
        assertNotEquals(actual, expected, null);
    }

    public static void assertNotEquals(float actual, float expected, String message) {
        fail("Float equality not a good test. Use assertNotEquals(float,float,float[,String]) instead");
    }

    public static void assertNotEquals(float actual, float expected) {
        assertNotEquals(actual, expected, null);
    }

    public static void assertNotEquals(float actual, float expected, float tolerance, String message) {
        assertFalse(Math.abs(actual - expected) <= tolerance, message);
    }

    public static void assertNotEquals(float actual, float expected, float tolerance) {
        assertNotEquals(actual, expected, tolerance, null);
    }

    public static void assertNotEquals(double actual, double expected, String message) {
        fail("Double equality not a good test. Use assertNotEquals(double,double,double[,String]) instead");
    }

    public static void assertNotEquals(double actual, double expected) {
        assertNotEquals(actual, expected, null);
    }

    public static void assertNotEquals(double actual, double expected, double tolerance, String message) {
        assertFalse(Math.abs(actual - expected) <= tolerance, message);
    }

    public static void assertNotEquals(double actual, double expected, double tolerance) {
        assertNotEquals(actual, expected, tolerance, null);
    }

    public static void assertNotEquals(char actual, char expected, String message) {
        assertFalse(actual == expected, message);
    }

    public static void assertNotEquals(char actual, char expected) {
        assertNotEquals(actual, expected, null);
    }

    // Assert Greater Than

    public static void assertGreaterThan(Comparable actual, Comparable expected, String message) {
        if (actual == null) {
            throw new NullPointerException("Actual must not be null");
        }
        if (expected == null) {
            throw new NullPointerException("Expected must not be null");
        }
        assertTrue(actual.compareTo(expected) == 1, message);
    }

    public static void assertGreaterThan(Comparable actual, Comparable expected) {
        assertGreaterThan(actual, expected, null);
    }

    public static void assertGreaterThan(byte actual, byte expected, String message) {
        assertTrue(actual > expected, message);
    }

    public static void assertGreaterThan(byte actual, byte expected) {
        assertGreaterThan(actual, expected, null);
    }

    public static void assertGreaterThan(short actual, short expected, String message) {
        assertTrue(actual > expected, message);
    }

    public static void assertGreaterThan(short actual, short expected) {
        assertGreaterThan(actual, expected, null);
    }

    public static void assertGreaterThan(int actual, int expected, String message) {
        assertTrue(actual > expected, message);
    }

    public static void assertGreaterThan(int actual, int expected) {
        assertGreaterThan(actual, expected, null);
    }

    public static void assertGreaterThan(long actual, long expected, String message) {
        assertTrue(actual > expected, message);
    }

    public static void assertGreaterThan(long actual, long expected) {
        assertGreaterThan(actual, expected, null);
    }

    public static void assertGreaterThan(float actual, float expected, String message) {
        assertTrue(actual > expected, message);
    }

    public static void assertGreaterThan(float actual, float expected) {
        assertGreaterThan(actual, expected, null);
    }

    public static void assertGreaterThan(double actual, double expected, String message) {
        assertTrue(actual > expected, message);
    }

    public static void assertGreaterThan(double actual, double expected) {
        assertGreaterThan(actual, expected, null);
    }

    public static void assertGreaterThan(char actual, char expected, String message) {
        assertTrue(actual > expected, message);
    }

    public static void assertGreaterThan(char actual, char expected) {
        assertGreaterThan(actual, expected, null);
    }

    // Assert Null / Not Null

    public static void assertNull(Object o, String message) {
        assertTrue(o == null, message);
    }

    public static void assertNull(Object o) {
        assertNull(o == null, null);
    }

    public static void assertNotNull(Object o, String message) {
        assertTrue(o != null, message);
    }

    public static void assertNotNull(Object o) {
        assertNotNull(o, null);
    }
}
