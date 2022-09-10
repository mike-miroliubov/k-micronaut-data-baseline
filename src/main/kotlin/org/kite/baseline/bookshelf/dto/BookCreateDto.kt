package org.kite.baseline.bookshelf.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class BookCreateDto(val title: String, val authorId: Long, val pages: Int)