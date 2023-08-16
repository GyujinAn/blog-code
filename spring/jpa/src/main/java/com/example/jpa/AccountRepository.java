package com.example.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findAllByOrganizationId(UUID organizationId);

    Optional<Account> findByName(String nameNotPk);
}
