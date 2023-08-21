package com.example.aop.audit;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
public class AuditAspect {

    private final AuditRepository auditRepository;

    @AfterReturning(
            pointcut = "@annotation(com.example.aop.audit.RestoreAudit)",
            returning = "auditable"
    )
    public void restoreAudit(Auditable auditable) {
        if (!auditable.isAuditable()) {
            throw new RuntimeException();
        }
        AuditDto auditDto = auditable.getAuditDto();
        Audit audit = new Audit();
        audit.targetId = auditDto.targetId;
        audit.auditType = auditDto.auditType;
        audit.subjectId = auditDto.subjectId;
        audit.detail = auditDto.auditDetail.toString();
        auditRepository.save(audit);
    }

}
