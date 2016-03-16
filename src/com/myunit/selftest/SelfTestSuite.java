package com.myunit.metatest;

import com.myunit.test.*;

import static com.myunit.assertion.Assert.*;

public class MetaTestSuite {
    @Test(expected = AssertionError.class)
    void testFail() {
        fail();
    }

    public static void main(String[] args){
        new TestRunner().run(
                MetaTestSuite.class,
                AssertTrueFalse.class,
                AssertEquals.class,
                AssertNotEquals.class,
                AssertGreaterThan.class
        );
    }
}
