package com.example.aop.audit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UpdateAccountUseCaseTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuditRepository auditRepository;

    @Autowired
    UpdateAccountUseCase updateAccountUseCase;

    @Transactional
    @Test
    public void audit() throws Exception {
        // given
        Account account = Account.createAccount();
        accountRepository.save(account);

        // when
        updateAccountUseCase.execute(
                new UpdateAccountDto(
                        account.id,
                        1L,
                        "nameToUpdate",
                        "addressToUpdate"
                )
        );

        // then
        Audit result = auditRepository.findAllByTargetId(account.id.toString()).get(0);
        assertEquals(AuditType.ACCOUNT, result.auditType);
        assertEquals(String.valueOf(account.id), result.targetId);;
        assertTrue(result.detail.contains("nameToUpdate"));
        assertTrue(result.detail.contains("addressToUpdate"));

    }

}
