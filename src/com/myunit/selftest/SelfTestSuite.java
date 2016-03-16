package com.myunit.selftest;

import com.myunit.assertion.TestFailedError;
import com.myunit.test.*;

import static com.myunit.assertion.Assert.*;

public class SelfTestSuite {
    @Test(expected = TestFailedError.class)
    void testFail() {
        fail();
    }

    @Test(expected = ArithmeticException.class)
    void test_RuntimeException() {
        int x = 5/0;
    }

    public static void main(String[] args){
        new TestRunner().run(
                SelfTestSuite.class,
                AssertTrueFalse.class,
                AssertEquals.class,
                AssertNotEquals.class,
                AssertGreaterThan.class
        );
    }
}
