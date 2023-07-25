package com.example.jpa.cashing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long organizationId;

    public String name;

    public Account() {

    }

    public Account(Long id, Long organizationId, String name) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
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
}
