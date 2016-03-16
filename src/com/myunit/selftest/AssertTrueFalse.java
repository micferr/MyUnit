package com.myunit.selftest;

import com.myunit.test.Test;

import static com.myunit.assertion.Assert.assertTrue;

public class AssertTrueFalse {
    @Test
    void testAssertTrue_actuallyTrue() {
        assertTrue(true);
    }

    @Test(expected = AssertionError.class)
    void testAssertTrue_actuallyFalse() {
        assertTrue(false);
    }
}
