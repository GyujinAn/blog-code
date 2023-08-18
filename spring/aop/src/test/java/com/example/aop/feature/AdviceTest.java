package com.example.aop.feature;

import com.example.aop.Account;
import com.example.aop.AccountRepository;
import com.example.aop.UpdateAccountDto;
import com.example.aop.UpdateAccountUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
public class AdviceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UpdateAccountUseCase updateAccountUseCase;

    @Transactional
    @Test
    public void testBeforeAdvice() throws Exception {

        // given
        Account account = new Account();
        accountRepository.save(account);

        // when
        updateAccountUseCase.execute(new UpdateAccountDto(account.id, UUID.randomUUID(), "new name", "new address"));


        // then

    }

}
