package com.myunit.assertion;

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

    static void assertEquals(Object actual, Object expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(byte actual, byte expected, String message) {
        assertTrue(actual == expected, message);
    }

    static void assertEquals(byte actual, byte expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(short actual, short expected, String message) {
        assertTrue(actual == expected, message);
    }

    static void assertEquals(short actual, short expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(int actual, int expected, String message) {
        assertTrue(actual == expected, message);
    }

    static void assertEquals(int actual, int expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(long actual, long expected, String message) {
        assertTrue(actual == expected, message);
    }

    static void assertEquals(long actual, long expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(float actual, float expected, String message) {
        fail("Float equality not a good test. Use assertEquals(float,float,float[,String]) instead");
    }

    static void assertEquals(float actual, float expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(float actual, float expected, float tolerance, String message) {
        assertTrue(Math.abs(actual - expected) <= tolerance, message);
    }

    static void assertEquals(float actual, float expected, float tolerance) {
        assertEquals(actual, expected, tolerance, null);
    }

    static void assertEquals(double actual, double expected, String message) {
        fail("Double equality not a good test. Use assertEquals(double,double,double[,String]) instead");
    }

    static void assertEquals(double actual, double expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(double actual, double expected, double tolerance, String message) {
        assertTrue(Math.abs(actual - expected) <= tolerance, message);
    }

    static void assertEquals(double actual, double expected, double tolerance) {
        assertEquals(actual, expected, tolerance, null);
    }

    static void assertEquals(char actual, char expected, String message) {
        assertTrue(actual == expected, message);
    }

    static void assertEquals(char actual, char expected) {
        assertEquals(actual, expected, null);
    }

    static void assertNotEquals(Object actual, Object expected, String message) {
        assertFalse(Objects.equals(actual, expected), message);
    }

    static void assertNotEquals(Object actual, Object expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(byte actual, byte expected, String message) {
        assertFalse(actual == expected, message);
    }

    static void assertNotEquals(byte actual, byte expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(short actual, short expected, String message) {
        assertFalse(actual == expected, message);
    }

    static void assertNotEquals(short actual, short expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(int actual, int expected, String message) {
        assertFalse(actual == expected, message);
    }

    static void assertNotEquals(int actual, int expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(long actual, long expected, String message) {
        assertFalse(actual == expected, message);
    }

    static void assertNotEquals(long actual, long expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(float actual, float expected, String message) {
        fail("Float equality not a good test. Use assertNotEquals(float,float,float[,String]) instead");
    }

    static void assertNotEquals(float actual, float expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(float actual, float expected, float tolerance, String message) {
        assertFalse(Math.abs(actual - expected) <= tolerance, message);
    }

    static void assertNotEquals(float actual, float expected, float tolerance) {
        assertNotEquals(actual, expected, tolerance, null);
    }

    static void assertNotEquals(double actual, double expected, String message) {
        fail("Double equality not a good test. Use assertNotEquals(double,double,double[,String]) instead");
    }

    static void assertNotEquals(double actual, double expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(double actual, double expected, double tolerance, String message) {
        assertFalse(Math.abs(actual - expected) <= tolerance, message);
    }

    static void assertNotEquals(double actual, double expected, double tolerance) {
        assertNotEquals(actual, expected, tolerance, null);
    }

    static void assertNotEquals(char actual, char expected, String message) {
        assertFalse(actual == expected, message);
    }

    static void assertNotEquals(char actual, char expected) {
        assertNotEquals(actual, expected, null);
    }
}
