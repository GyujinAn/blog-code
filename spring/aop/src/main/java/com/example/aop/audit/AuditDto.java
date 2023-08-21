package com.example.aop.audit;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {
    String targetId;

    String subjectId;

    AuditType auditType;

    Map<String, String> auditDetail;
}
