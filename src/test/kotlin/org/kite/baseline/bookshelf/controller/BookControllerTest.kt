package org.kite.baseline.bookshelf.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.kite.baseline.bookshelf.dto.BookCreateDto
import org.kite.baseline.bookshelf.model.Book
import org.kite.baseline.bookshelf.service.BookService
import org.mockito.kotlin.mock

@MicronautTest(packages = ["org.kite.baseline.bookshelf.controller"])
class BookControllerTest {
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @MockBean(BookService::class)
    fun mockBookService() = BookService(mock(), mock())

    @Test
    internal fun `should return 404 when no book found`() {
        // given
        val randomBookId = 42

        // when-then
        assertThatThrownBy { client.toBlocking().retrieve(HttpRequest.GET<Any>("/books/$randomBookId")) }
            .isExactlyInstanceOf(HttpClientResponseException::class.java)
            .matches(hasHttpStatus(HttpStatus.NOT_FOUND))
            .hasMessage("Book with id '42' not found")
    }

    @Test
    internal fun `should return 400 when creating book for non-existing author`() {
        // given
        val book = BookCreateDto("Book with no author", 42, 111)

        // when-then
        assertThatThrownBy { client.toBlocking().retrieve(HttpRequest.POST("/books", book), Book::class.java) }
            .isExactlyInstanceOf(HttpClientResponseException::class.java)
            .matches(hasHttpStatus(HttpStatus.BAD_REQUEST))
            .hasMessage("Author with ID 42 not found")
    }

    private fun hasHttpStatus(httpStatus: HttpStatus): (Throwable) -> Boolean =
        { e -> e is HttpClientResponseException && e.status == httpStatus }

}