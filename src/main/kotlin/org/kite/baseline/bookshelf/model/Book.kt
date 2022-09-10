package org.kite.baseline.bookshelf.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation

@MappedEntity
data class Book(
    @field:Id @GeneratedValue
    val id: Long?,
    val title: String,
    val pages: Int,
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    val author: Author
)
