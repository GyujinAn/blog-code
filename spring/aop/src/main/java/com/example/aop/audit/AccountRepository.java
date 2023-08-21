package com.example.aop.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByOrganizationId(UUID organizationId);

    Optional<Account> findByName(String nameNotPk);

    @Query("select a from Account a where a.id = :id")
    Optional<Account> findByIdJpql(UUID id);

}
