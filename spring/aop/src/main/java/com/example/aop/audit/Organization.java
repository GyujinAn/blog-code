package com.example.aop.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
public class Organization implements Auditable{
    @Id
    public UUID id;

    @Column
    public String name;

    @Transient
    private Boolean isAuditable = false;

    @Transient
    private AuditDto auditDto;

    public Organization() {
        this.id = UUID.randomUUID();
    }

    public Organization update(UpdateOrganizationDto dto) {
        isAuditable = true;
        Map<String, String> auditDetail = new HashMap<>();
        if (!this.name.equals(dto.name)) {
            String detail = this.name + " -> " + dto.name;
            auditDetail.put("name", detail);
            this.name = dto.name;
        }
        auditDto = new AuditDto();
        auditDto.subjectId = UUID.randomUUID().toString();
        auditDto.auditType = AuditType.ORGANIZATION;
        auditDto.auditDetail = auditDetail;
        return this;
    }

    @Override
    public boolean isAuditable() {
        return isAuditable;
    }

    @Override
    public AuditDto getAuditDto() {
        if (!isAuditable) {
            throw new RuntimeException();
        }
        isAuditable = false;
        return auditDto;
    }
}
