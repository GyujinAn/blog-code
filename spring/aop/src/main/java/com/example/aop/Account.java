package com.example.aop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Account {

    @Id
    public UUID id;
    @Column
    public UUID organizationId;

    @Column
    public String name;

    @Column
    public String address;

    public Account() {
        id = UUID.randomUUID();
    }

    public Account(UUID id, UUID organizationId, String name) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
    }

    public Account(UUID id, UUID organizationId, String name, String address) {
        this.id = id;
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
        this.organizationId = dto.organizationId;
        this.name = dto.name;
        this.address = dto.address;
        return this;
    }
}
