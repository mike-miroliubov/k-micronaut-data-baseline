package org.kite.baseline.bookshelf.repository

import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Join
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.repository.PageableRepository
import org.kite.baseline.bookshelf.model.Author
import org.kite.baseline.bookshelf.model.Book

@JdbcRepository
@Join(value = "author", type = Join.Type.FETCH)
interface BookRepository : PageableRepository<Book, Long> {
    @Executable // TODO: why??
    fun findByTitleIlike(title: String): Iterable<Book>

    @Executable
    fun findByAuthorAndTitle(author: Author, title: String): Book?

    @Executable
    fun findByAuthor(author: Author): Iterable<Book>
}