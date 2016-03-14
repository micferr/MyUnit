package com.myunit.assertion;

import java.util.Objects;

interface AssertEquals {
    static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertTrue(Objects.equals(actual, expected), message);
    }

    static void assertEquals(Object actual, Object expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(byte actual, byte expected, String message) {
        Assert.assertTrue(actual == expected, message);
    }

    static void assertEquals(byte actual, byte expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(short actual, short expected, String message) {
        Assert.assertTrue(actual == expected, message);
    }

    static void assertEquals(short actual, short expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(int actual, int expected, String message) {
        Assert.assertTrue(actual == expected, message);
    }

    static void assertEquals(int actual, int expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(long actual, long expected, String message) {
        Assert.assertTrue(actual == expected, message);
    }

    static void assertEquals(long actual, long expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(float actual, float expected, String message) {
        Assert.fail("Float equality not a good test. Use assertEquals(float,float,float[,String]) instead");
    }

    static void assertEquals(float actual, float expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(float actual, float expected, float tolerance, String message) {
        Assert.assertTrue(Math.abs(actual - expected) <= tolerance, message);
    }

    static void assertEquals(float actual, float expected, float tolerance) {
        assertEquals(actual, expected, tolerance, null);
    }

    static void assertEquals(double actual, double expected, String message) {
        Assert.fail("Double equality not a good test. Use assertEquals(double,double,double[,String]) instead");
    }

    static void assertEquals(double actual, double expected) {
        assertEquals(actual, expected, null);
    }

    static void assertEquals(double actual, double expected, double tolerance, String message) {
        Assert.assertTrue(Math.abs(actual - expected) <= tolerance, message);
    }

    static void assertEquals(double actual, double expected, double tolerance) {
        assertEquals(actual, expected, tolerance, null);
    }

    static void assertEquals(char actual, char expected, String message) {
        Assert.assertTrue(actual == expected, message);
    }

    static void assertEquals(char actual, char expected) {
        assertEquals(actual, expected, null);
    }
}
