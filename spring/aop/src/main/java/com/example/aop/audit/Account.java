package com.example.aop.audit;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Account implements Auditable{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;
    @Column
    public Long organizationId;

    @Column
    public String name;

    @Column
    public String address;

    @Transient
    private Boolean isAuditable = false;

    @Transient
    private AuditDto auditDto;

    public Account() {

    }

    static Account createAccount() {
        return new Account(
                -1L,
                "default",
                "default"
        );
    }
    public Account(Long organizationId, String name, String address) {
        this.organizationId = organizationId;
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(organizationId, account.organizationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationId);
    }

    public Account update(UpdateAccountDto dto) {
        isAuditable = true;
        Map<String, String> auditDetail = new HashMap<>();
        if (!this.organizationId.equals(dto.organizationId)) {
            String detail = this.organizationId + " -> " + dto.organizationId;
            auditDetail.put("organizationId", detail);
            this.organizationId = dto.organizationId;
        }
        if (!this.name.equals(dto.name)) {
            String detail = this.name + " -> " + dto.name;
            auditDetail.put("name", detail);
            this.name = dto.name;
        }
        if (!this.address.equals(dto.address)) {
            String detail = this.address + " -> " + dto.address;
            auditDetail.put("address", detail);
            this.address = dto.address;
        }
        auditDto = new AuditDto();
        auditDto.targetId = String.valueOf(id);
        auditDto.subjectId = UUID.randomUUID().toString();
        auditDto.auditType = AuditType.ACCOUNT;
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
