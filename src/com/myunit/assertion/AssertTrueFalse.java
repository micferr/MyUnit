package com.myunit.assertion;

import static com.myunit.assertion.Assert.*;

public class AssertTrueFalse {
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
}
