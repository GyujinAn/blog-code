package com.example.aop.feature;

import com.example.aop.sample.SampleDto;
import com.example.aop.sample.SampleUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class AdviceTest {

    @Autowired
    SampleUseCase sampleUseCase;

    @Transactional
    @Test
    public void testAdvice() throws Exception {
        // given
        SampleDto sampleDto = new SampleDto("1", "2", "3");

        // when
        sampleUseCase.execute(sampleDto);

        // then

    }

}
