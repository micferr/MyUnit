package com.myunit.selftest;

import com.myunit.assertion.TestFailedError;
import com.myunit.test.Test;

import static com.myunit.assertion.Assert.assertNotEquals;

public class AssertNotEquals {
    @Test
    void testAssertNotEquals_actuallyNotEquals() {
        assertNotEquals(null, new Object());
        assertNotEquals(new Object(), null);
        assertNotEquals(1, new Object());
        assertNotEquals(new Object(), "");
        assertNotEquals((byte)0, (byte)1);
        assertNotEquals((short)2, (short)3);
        assertNotEquals(4, 5);
        assertNotEquals((long)6, (long)7);
        assertNotEquals(0.f, 1.f, 0.1f);
        assertNotEquals(2.d, 3.d, 0.1d);
        assertNotEquals('a', 'b');
        assertNotEquals(true, false);
        assertNotEquals(false, true);
    }

    @Test(expected = TestFailedError.class)
    void testAssertNotEquals_actuallyEquals_null_null() {
        assertNotEquals(null, null);
    }
}
