package com.myunit.assertion;

public class Assert
        implements AssertEquals, AssertNotEquals {
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

    public static void fail() {
        throw new AssertionError();
    }

    public static void fail(String message) {
        throw new AssertionError(message);
    }
}
