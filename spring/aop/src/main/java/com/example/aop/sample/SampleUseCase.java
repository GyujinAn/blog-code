package com.example.aop.sample;

import org.springframework.stereotype.Component;

@Component
public class SampleUseCase {

    @SampleAnnotation
    public SampleDto execute(SampleDto dto) {
        return dto;
    }

}
