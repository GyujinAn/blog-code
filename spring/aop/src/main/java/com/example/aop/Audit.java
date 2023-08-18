package com.example.aop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Audit {

    @Id
    public UUID id;

    @Column
    public AuditType auditType;

    @Column
    public UUID subjectId;

    @Column
    public String detail;


}
