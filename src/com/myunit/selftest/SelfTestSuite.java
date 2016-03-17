package com.myunit.selftest;

import com.myunit.test.*;

public class SelfTestSuite {
    public static void main(String[] args){
        new TestRunner().run(
                AssertMiscellaneous.class,
                AssertTrueFalse.class,
                AssertEquals.class,
                AssertNotEquals.class,
                AssertGreaterThan.class
        );
    }
}
