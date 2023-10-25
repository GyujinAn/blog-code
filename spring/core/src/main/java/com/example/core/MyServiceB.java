package com.example.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyServiceB {
    private final String name;

    private static final MyServiceB MY_SERVICE_B = new MyServiceB("b");
    public static MyServiceB createMyBeanB() {
        return MY_SERVICE_B;
    }
}
