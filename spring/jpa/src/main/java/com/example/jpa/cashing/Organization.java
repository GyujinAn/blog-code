package com.example.jpa.cashing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Organization {
    @Id
    public UUID id;

    public Organization() {
        this.id = UUID.randomUUID();
    }

    public Organization(UUID id) {
        this.id = id;
    }
}
