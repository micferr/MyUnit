package com.myunit.selftest;

import com.myunit.log.HTMLLogger;
import com.myunit.test.*;

public class SelfTestSuite {
    public static void main(String[] args){
        new TestRunner(new HTMLLogger("log.html")).run(
                AssertMiscellaneous.class,
                AssertTrueFalse.class,
                AssertEquals.class,
                AssertNotEquals.class,
                AssertGreaterThan.class,
                AssertLessThan.class
        );
    }
}
