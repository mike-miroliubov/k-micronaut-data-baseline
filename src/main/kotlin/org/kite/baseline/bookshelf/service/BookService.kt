package org.kite.baseline.bookshelf.service

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.kite.baseline.bookshelf.dto.BookCreateDto
import org.kite.baseline.bookshelf.exception.InvalidInputException
import org.kite.baseline.bookshelf.exception.NotFoundException
import org.kite.baseline.bookshelf.model.Author
import org.kite.baseline.bookshelf.model.Book
import org.kite.baseline.bookshelf.repository.AuthorRepository
import org.kite.baseline.bookshelf.repository.BookRepository
import javax.transaction.Transactional

@Singleton
open class BookService(
    @Inject private val bookRepository: BookRepository,
    @Inject private val authorRepository: AuthorRepository) {

    fun listBooks(prefix: String?): Iterable<Book> {
        if (prefix.isNullOrEmpty()) {
            return bookRepository.findAll()
        }

        return bookRepository.findByTitleIlike("${prefix}%")
    }

    fun getBooksByAuthor(author: Author) = bookRepository.findByAuthor(author)

    @Transactional
    open fun createBook(book: BookCreateDto): Book {
        val author = authorRepository.findById(book.authorId)
            .orElseThrow { InvalidInputException("Author with ID ${book.authorId} not found") }

        val existingBook = bookRepository.findByAuthorAndTitle(author, book.title)
        if (existingBook != null) {
            throw InvalidInputException("Book '${book.title}' by '${author.name}' already exists")
        }

        return bookRepository.save(Book(null, book.title, book.pages, author))
    }

    fun getBook(id: Long): Book = bookRepository.findById(id).orElseThrow { NotFoundException("Book with id '$id' not found") }
}