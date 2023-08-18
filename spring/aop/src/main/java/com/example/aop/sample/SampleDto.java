package com.example.aop.sample;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SampleDto {
    public String sample1;
    public String sample2;
    public String sample3;

    @Override
    public String toString() {
        return "SampleDto{" +
                "sample1='" + sample1 + '\'' +
                ", sample2='" + sample2 + '\'' +
                ", sample3='" + sample3 + '\'' +
                '}';
    }
}
