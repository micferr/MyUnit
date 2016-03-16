package com.myunit.assertion;

import java.util.Objects;

@SuppressWarnings("unused")
public class Assert {

    public static void fail() {
        throw new TestFailedError();
    }

    public static void fail(String message) {
        throw new TestFailedError(message);
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

    // Assert Less Than

    public static void assertLessThan(byte actual, byte expected, String message) {
        assertGreaterThan(expected, actual, message);
    }

    public static void assertLessThan(byte actual, byte expected) {
        assertLessThan(actual, expected, null);
    }

    public static void assertLessThan(short actual, short expected, String message) {
        assertGreaterThan(expected, actual, message);
    }

    public static void assertLessThan(short actual, short expected) {
        assertLessThan(actual, expected, null);
    }

    public static void assertLessThan(int actual, int expected, String message) {
        assertGreaterThan(expected, actual, message);
    }

    public static void assertLessThan(int actual, int expected) {
        assertLessThan(actual, expected, null);
    }

    public static void assertLessThan(long actual, long expected, String message) {
        assertGreaterThan(expected, actual, message);
    }

    public static void assertLessThan(long actual, long expected) {
        assertLessThan(actual, expected, null);
    }

    public static void assertLessThan(float actual, float expected, String message) {
        assertGreaterThan(expected, actual, message);
    }

    public static void assertLessThan(float actual, float expected) {
        assertLessThan(actual, expected, null);
    }

    public static void assertLessThan(double actual, double expected, String message) {
        assertGreaterThan(expected, actual, message);
    }

    public static void assertLessThan(double actual, double expected) {
        assertLessThan(actual, expected, null);
    }

    public static void assertLessThan(char actual, char expected, String message) {
        assertGreaterThan(expected, actual, message);
    }

    public static void assertLessThan(char actual, char expected) {
        assertLessThan(actual, expected, null);
    }

    // Assert Greater Than Or Equals

    public static void assertGreaterThanOrEquals(byte actual, byte expected, String message) {
        assertTrue(actual >= expected, message);
    }

    public static void assertGreaterThanOrEquals(byte actual, byte expected) {
        assertGreaterThanOrEquals(actual, expected, null);
    }

    public static void assertGreaterThanOrEquals(short actual, short expected, String message) {
        assertTrue(actual >= expected, message);
    }

    public static void assertGreaterThanOrEquals(short actual, short expected) {
        assertGreaterThanOrEquals(actual, expected, null);
    }

    public static void assertGreaterThanOrEquals(int actual, int expected, String message) {
        assertTrue(actual >= expected, message);
    }

    public static void assertGreaterThanOrEquals(int actual, int expected) {
        assertGreaterThanOrEquals(actual, expected, null);
    }

    public static void assertGreaterThanOrEquals(long actual, long expected, String message) {
        assertTrue(actual >= expected, message);
    }

    public static void assertGreaterThanOrEquals(long actual, long expected) {
        assertGreaterThanOrEquals(actual, expected, null);
    }

    public static void assertGreaterThanOrEquals(float actual, float expected, String message) {
        assertTrue(actual >= expected, message);
    }

    public static void assertGreaterThanOrEquals(float actual, float expected) {
        assertGreaterThanOrEquals(actual, expected, null);
    }

    public static void assertGreaterThanOrEquals(double actual, double expected, String message) {
        assertTrue(actual >= expected, message);
    }

    public static void assertGreaterThanOrEquals(double actual, double expected) {
        assertGreaterThanOrEquals(actual, expected, null);
    }

    public static void assertGreaterThanOrEquals(char actual, char expected, String message) {
        assertTrue(actual >= expected, message);
    }

    public static void assertGreaterThanOrEquals(char actual, char expected) {
        assertGreaterThanOrEquals(actual, expected, null);
    }

    // Assert Less Than Or Equals

    public static void assertLessThanOrEquals(byte actual, byte expected, String message) {
        assertTrue(actual <= expected, message);
    }

    public static void assertLessThanOrEquals(byte actual, byte expected) {
        assertLessThanOrEquals(actual, expected, null);
    }

    public static void assertLessThanOrEquals(short actual, short expected, String message) {
        assertTrue(actual <= expected, message);
    }

    public static void assertLessThanOrEquals(short actual, short expected) {
        assertLessThanOrEquals(actual, expected, null);
    }

    public static void assertLessThanOrEquals(int actual, int expected, String message) {
        assertTrue(actual <= expected, message);
    }

    public static void assertLessThanOrEquals(int actual, int expected) {
        assertLessThanOrEquals(actual, expected, null);
    }

    public static void assertLessThanOrEquals(long actual, long expected, String message) {
        assertTrue(actual <= expected, message);
    }

    public static void assertLessThanOrEquals(long actual, long expected) {
        assertLessThanOrEquals(actual, expected, null);
    }

    public static void assertLessThanOrEquals(float actual, float expected, String message) {
        assertTrue(actual <= expected, message);
    }

    public static void assertLessThanOrEquals(float actual, float expected) {
        assertLessThanOrEquals(actual, expected, null);
    }

    public static void assertLessThanOrEquals(double actual, double expected, String message) {
        assertTrue(actual <= expected, message);
    }

    public static void assertLessThanOrEquals(double actual, double expected) {
        assertLessThanOrEquals(actual, expected, null);
    }

    public static void assertLessThanOrEquals(char actual, char expected, String message) {
        assertTrue(actual <= expected, message);
    }

    public static void assertLessThanOrEquals(char actual, char expected) {
        assertLessThanOrEquals(actual, expected, null);
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
