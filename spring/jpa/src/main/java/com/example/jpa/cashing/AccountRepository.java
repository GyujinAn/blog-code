package com.example.jpa.cashing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public List<Account> findAllByOrganizationId(Long organizationId);
}
