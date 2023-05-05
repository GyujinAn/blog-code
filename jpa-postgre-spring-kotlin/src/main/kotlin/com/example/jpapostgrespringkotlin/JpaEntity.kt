package com.example.jpapostgrespringkotlin

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef


@Entity
@Table(name = "jpa")
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType::class
)
class JpaEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Type(type = "jsonb")
    @Column(name = "jsonb1", columnDefinition = "jsonb")
    private var jsonb: Jsonb = Jsonb(),

    @Type(type = "jsonb")
    @Column(name = "jsonb2", columnDefinition = "jsonb")
    private var jsonb2: Any = Jsonb()

)

data class Jsonb (
    val byte: Byte = 1,
    val short: Short = 2,
    val int: Int = 3,
    val long: Long = 4,
    val float: Float = 5f,
    val double: Double = 6.0,
    val char: Char = 'a',
    val boolean: Boolean = true,
    val string: String = "hello",
)

//Byte: 8-bit signed integer
//Short: 16-bit signed integer
//Int: 32-bit signed integer
//Long: 64-bit signed integer
//Float: 32-bit floating-point number
//Double: 64-bit floating-point number
//Char: A single character
//Boolean: True or false value
//String: A sequence of characters
//Any: The root of the Kotlin class hierarchy
//Unit: A type that represents the absence of a meaningful value
//Array: A generic type for arrays of any type
//List: A read-only collection of elements
//MutableList: A mutable collection of elements
//Set: A read-only collection of unique elements
//MutableSet: A mutable collection of unique elements
//Map: A read-only collection of key-value pairs
//MutableMap: A mutable collection of key-value pairs
//Sequence: A lazily evaluated collection of elements
//Iterable: An interface for collections that can be iterated over
