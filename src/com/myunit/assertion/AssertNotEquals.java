package com.myunit.assertion;

import java.util.Objects;

import static com.myunit.assertion.Assert.assertFalse;
import static com.myunit.assertion.Assert.fail;

interface AssertNotEquals {
    static void assertNotEquals(Object actual, Object expected, String message) {
        Assert.assertFalse(Objects.equals(actual, expected), message);
    }

    static void assertNotEquals(Object actual, Object expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(byte actual, byte expected, String message) {
        Assert.assertFalse(actual == expected, message);
    }

    static void assertNotEquals(byte actual, byte expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(short actual, short expected, String message) {
        Assert.assertFalse(actual == expected, message);
    }

    static void assertNotEquals(short actual, short expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(int actual, int expected, String message) {
        Assert.assertFalse(actual == expected, message);
    }

    static void assertNotEquals(int actual, int expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(long actual, long expected, String message) {
        Assert.assertFalse(actual == expected, message);
    }

    static void assertNotEquals(long actual, long expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(float actual, float expected, String message) {
        Assert.fail("Float equality not a good test. Use assertNotEquals(float,float,float[,String]) instead");
    }

    static void assertNotEquals(float actual, float expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(float actual, float expected, float tolerance, String message) {
        Assert.assertFalse(Math.abs(actual - expected) <= tolerance, message);
    }

    static void assertNotEquals(float actual, float expected, float tolerance) {
        assertNotEquals(actual, expected, tolerance, null);
    }

    static void assertNotEquals(double actual, double expected, String message) {
        Assert.fail("Double equality not a good test. Use assertNotEquals(double,double,double[,String]) instead");
    }

    static void assertNotEquals(double actual, double expected) {
        assertNotEquals(actual, expected, null);
    }

    static void assertNotEquals(double actual, double expected, double tolerance, String message) {
        Assert.assertFalse(Math.abs(actual - expected) <= tolerance, message);
    }

    static void assertNotEquals(double actual, double expected, double tolerance) {
        assertNotEquals(actual, expected, tolerance, null);
    }

    static void assertNotEquals(char actual, char expected, String message) {
        Assert.assertFalse(actual == expected, message);
    }

    static void assertNotEquals(char actual, char expected) {
        assertNotEquals(actual, expected, null);
    }
}
