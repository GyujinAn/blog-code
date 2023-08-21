package com.example.aop.audit;

public interface Auditable {
    boolean isAuditable();
    AuditDto getAuditDto();
}
