package com.example.core;

public class MyServiceLocator {

    private static MyServiceC myServiceC = new MyServiceC("c");

    public MyServiceC createMyServiceC() {
        return myServiceC;
    }
}
