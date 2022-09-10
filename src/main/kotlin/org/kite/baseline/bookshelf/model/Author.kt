package org.kite.baseline.bookshelf.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity


@MappedEntity
data class Author(
    @field:Id
    @GeneratedValue
    val id: Long?,
    val name: String
)