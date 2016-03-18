package com.myunit.selftest;

import com.myunit.log.HTMLLogger;
import com.myunit.test.*;

public class SelfTestSuite {
    public static void main(String[] args){
        new TestRunner(new HTMLLogger("log.html")).run(
                TestAssertMiscellaneous.class,
                TestAssertTrueFalse.class,
                TestAssertEquals.class,
                TestAssertNotEquals.class,
                TestAssertGreaterThan.class,
                TestAssertLessThan.class
        );
    }
}
