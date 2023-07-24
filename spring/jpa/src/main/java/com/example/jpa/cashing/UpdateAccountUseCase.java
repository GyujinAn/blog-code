package com.example.jpa.cashing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class UpdateAccountUseCase {

    private final OrganizationRepository organizationRepository;

    private final AccountRepository accountRepository;

    @Transactional
    public void execute(Long accountId, Long organizationId) {
        Account account = accountRepository.findById(accountId).get();
        account.organizationId = organizationId;
    }
}
