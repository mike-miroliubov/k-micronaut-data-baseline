package org.kite.baseline.bookshelf.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable


@MappedEntity
@Serdeable
data class Author(
    @field:Id
    @GeneratedValue
    val id: Long?,
    val name: String
)