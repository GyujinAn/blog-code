package com.example.aop.audit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuditRepository extends JpaRepository<Audit, UUID> {

    List<Audit> findAllByTargetId(String targetId);

}
