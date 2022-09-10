package org.kite.baseline.bookshelf.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import jakarta.inject.Inject
import org.kite.baseline.bookshelf.service.AuthorService

@Controller("/authors")
class AuthorController(@Inject val authorService: AuthorService) {
    @Get("/{id}/books")
    fun getBooksByAuthor(@PathVariable id: Long) = authorService.getBooksByAuthor(id)
}