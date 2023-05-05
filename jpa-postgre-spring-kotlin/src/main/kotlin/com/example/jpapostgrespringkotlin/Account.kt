package com.example.jpapostgrespringkotlin

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef


@Entity
@Table(name = "account")
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType::class
)
class Account(

    @Column(name = "name")
    var name: String? = null,

    @Type(type = "jsonb")
    @Column(name = "history", columnDefinition = "jsonb")
    var history: Any? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
}
