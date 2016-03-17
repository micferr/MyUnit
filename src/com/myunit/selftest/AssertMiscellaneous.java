package com.myunit.selftest;

import static com.myunit.assertion.Assert.*;
import com.myunit.assertion.TestFailedError;
import com.myunit.test.Test;

import static com.myunit.assertion.Assert.fail;

public class AssertMiscellaneous {
    @Test(expected = TestFailedError.class)
    void testFail() {
        fail();
    }

    @Test(expected = ArithmeticException.class)
    void testRuntimeException() {
        int x = 5/0;
    }
}
