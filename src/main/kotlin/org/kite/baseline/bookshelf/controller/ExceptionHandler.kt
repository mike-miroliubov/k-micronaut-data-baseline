package org.kite.baseline.bookshelf.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateoas.JsonError
import org.kite.baseline.bookshelf.exception.InvalidInputException
import org.kite.baseline.bookshelf.exception.NotFoundException


/**
 * Fake controller to declare exception handlers
 */
@Controller
class ExceptionHandler {
    @Error(global = true, exception = InvalidInputException::class)
    fun invalidInputError(request: HttpRequest<*>, e: InvalidInputException): HttpResponse<JsonError> {
        val error = JsonError(e.message)

        return HttpResponse.badRequest<JsonError>()
            .body(error)
    }

    @Error(global = true, exception = NotFoundException::class)
    fun notFoundException(request: HttpRequest<*>, e: NotFoundException): HttpResponse<JsonError> =
        HttpResponse.notFound(JsonError(e.message))
}