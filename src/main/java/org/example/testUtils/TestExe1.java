package org.example.testUtils;

import org.example.testUtils.Executable;

public class TestExe1 implements Executable {
    public TestExe1() {
    }

    @Override
    public void execute() {
        System.out.println("This line is result of Execute() method in TestExe1");
    }
    public void someMethod1(){
    }
}
