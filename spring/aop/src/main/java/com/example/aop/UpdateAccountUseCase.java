package com.example.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    @Transactional
    public Account execute(UpdateAccountDto dto) throws Exception {
        Account account;
        Optional<Account> optionalAccount = accountRepository.findById(dto.accountId);
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else  {
            throw new Exception();
        }

        return account.update(dto);
    }

    @Transactional
    public void execute() throws Exception {

    }

}
