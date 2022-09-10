package org.kite.baseline.bookshelf.controller

import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import org.kite.baseline.bookshelf.dto.BookCreateDto
import org.kite.baseline.bookshelf.service.BookService

@Controller("/books")
class BookController(@Inject private val bookService: BookService) {
    @Get
    fun getBooks(@QueryValue prefix: String?) = bookService.listBooks(prefix)

    @Post
    fun createBook(@Body bookDto: BookCreateDto) = bookService.createBook(bookDto)

    @Get("/{id}")
    fun getBook(@PathVariable id: Long) = bookService.getBook(id)
}