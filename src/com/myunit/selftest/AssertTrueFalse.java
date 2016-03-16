package com.myunit.selftest;

import com.myunit.test.Test;

import static com.myunit.assertion.Assert.*;

public class AssertTrueFalse {
    @Test
    void testAssertTrue_actuallyTrue() {
        assertTrue(true);
    }

    @Test(expected = AssertionError.class)
    void testAssertTrue_actuallyFalse() {
        assertTrue(false);
    }

    @Test
    void testAssertFalse_actuallyFalse() {
        assertFalse(false);
    }

    @Test(expected = AssertionError.class)
    void testAssertFalse_actuallyTrue() {
        assertFalse(true);
    }
}
