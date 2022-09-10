package org.kite.baseline.bookshelf.service

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.kite.baseline.bookshelf.exception.NotFoundException
import org.kite.baseline.bookshelf.model.Book
import org.kite.baseline.bookshelf.repository.AuthorRepository

@Singleton
class AuthorService(@Inject val authorRepository: AuthorRepository,
                    @Inject val bookService: BookService) {
    fun getBooksByAuthor(id: Long): Iterable<Book> {
        val author = authorRepository.findById(id).orElseThrow { NotFoundException("Author with id '$id' not found") }
        return bookService.getBooksByAuthor(author)
    }
}