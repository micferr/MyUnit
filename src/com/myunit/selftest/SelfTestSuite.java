package com.myunit.selftest;

import com.myunit.assertion.TestFailedError;
import com.myunit.test.*;

import java.io.PrintStream;

import static com.myunit.assertion.Assert.*;

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
