package org.kite.baseline.bookshelf.controller

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.kite.baseline.bookshelf.dto.BookCreateDto
import org.kite.baseline.bookshelf.model.Author
import org.kite.baseline.bookshelf.model.Book
import org.kite.baseline.bookshelf.repository.AuthorRepository
import org.kite.baseline.bookshelf.service.BookService

@MicronautTest
internal class BookControllerIT {
    @Inject
    lateinit var bookService: BookService

    @Inject
    lateinit var authorRepository: AuthorRepository

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `should retrieve books for prefix` () {
        // given
        val author = authorRepository.save(Author(null, "test"))
        bookService.createBook(BookCreateDto("test", author.id!!, 42))

        // when
        val books = client.toBlocking().retrieve(HttpRequest.GET<Any>("/books"), Argument.listOf(Book::class.java))

        // then
        assertThat(books).hasSize(1)
    }

}