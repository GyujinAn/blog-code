package com.example.aop.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Audit {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;

    @Column
    public String targetId;

    @Column
    public AuditType auditType;

    @Column
    public String subjectId;

    @Column
    public String detail;
}
