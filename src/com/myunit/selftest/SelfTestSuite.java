package com.myunit.selftest;

import com.myunit.test.*;

import static com.myunit.assertion.Assert.*;

public class SelfTestSuite {
    @Test(expected = AssertionError.class)
    void testFail() {
        fail();
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
