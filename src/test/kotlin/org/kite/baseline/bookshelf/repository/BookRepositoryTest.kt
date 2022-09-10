package org.kite.baseline.bookshelf.repository

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.kite.baseline.bookshelf.model.Author
import org.kite.baseline.bookshelf.model.Book

@MicronautTest
internal class BookRepositoryTest {
    @Inject
    lateinit var authorRepository: AuthorRepository

    @Inject
    lateinit var bookRepository: BookRepository

    @Test
    fun `should save and load books`() {
        // given
        val author = authorRepository.save(Author(null, "test"))
        val book = Book(null, "test", 42, author)
        val saved = bookRepository.save(book)

        // when
        val loaded = bookRepository.findById(saved.id!!).get()

        // then
        assertThat(saved).usingRecursiveComparison().ignoringFields("id").isEqualTo(book)
        assertThat(loaded).isEqualTo(saved)
    }

    @Test
    internal fun `should search by name`() {
        val author = authorRepository.save(Author(null, "test"))
        val book = Book(null, "test of prefix search", 42, author)
        val saved = bookRepository.save(book)

        // when
        val results = bookRepository.findByTitleIlike("test%")

        // then
        assertThat(results).containsExactly(saved)
    }
}