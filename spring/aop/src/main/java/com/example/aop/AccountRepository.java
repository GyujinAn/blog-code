package com.example.aop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findAllByOrganizationId(UUID organizationId);

    Optional<Account> findByName(String nameNotPk);

    @Query("select a from Account a where a.id = :id")
    Optional<Account> findByIdJpql(UUID id);

}
