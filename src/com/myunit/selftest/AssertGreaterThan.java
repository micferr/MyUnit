package com.myunit.metatest;

import com.myunit.test.Test;

import static com.myunit.assertion.Assert.assertGreaterThan;

public class AssertGreaterThan {
    @Test
    void testAssertGreaterThan_actuallyGreaterThan() {
        assertGreaterThan((byte) 1, (byte) 0);
        assertGreaterThan((short) 3, (short) 1);
        assertGreaterThan(5, 4);
        assertGreaterThan((long) 7, (long) 6);
        assertGreaterThan(1.f, 0.f);
        assertGreaterThan(3.d, 2.d);
        assertGreaterThan('b', 'a');
    }
}
