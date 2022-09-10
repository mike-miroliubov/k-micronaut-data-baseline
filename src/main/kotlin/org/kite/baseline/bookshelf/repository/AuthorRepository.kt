package org.kite.baseline.bookshelf.repository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.repository.CrudRepository
import org.kite.baseline.bookshelf.model.Author

@JdbcRepository
interface AuthorRepository : CrudRepository<Author, Long>