package com.example.aop.sample;

import com.example.aop.UpdateAccountDto;
import org.springframework.stereotype.Component;

@Component
public class SampleUseCase {
    public SampleDto execute(SampleDto dto) {
        return dto;
    }

}
