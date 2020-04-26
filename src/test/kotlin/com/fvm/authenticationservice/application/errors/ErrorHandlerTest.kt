package com.fvm.authenticationservice.application.errors

import com.fvm.authenticationservice.application.errors.ErrorHandler
import com.fvm.authenticationservice.domain.exceptions.ValidationException
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.mockk.mockk
import io.mockk.verify
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Test
import java.sql.SQLException

internal class ErrorHandlerTest {

    @Test
    fun `given a ValidationException must get it status and handle the exception`() {
        val errorHandler = ErrorHandler()
        val validationException =
            ValidationException(message = "")
        val context = mockk<Context>(relaxed = true)

        errorHandler.handleException(validationException, context)

        verify(exactly = 1) { context.status(HttpStatus.UNPROCESSABLE_ENTITY_422) }
    }

    @Test
    fun `given a BadRequestResponse must get it status and handle the exception`() {
        val errorHandler = ErrorHandler()
        val badRequestResponse = BadRequestResponse(message = "")
        val context = mockk<Context>(relaxed = true)

        errorHandler.handleException(badRequestResponse, context)

        verify(exactly = 1) { context.status(HttpStatus.BAD_REQUEST_400) }
    }

    @Test
    fun `given a SQLException must get it status and handle the exception`() {
        val errorHandler = ErrorHandler()
        val sqlException = SQLException("")
        val context = mockk<Context>(relaxed = true)

        errorHandler.handleException(sqlException, context)

        verify(exactly = 1) { context.status(HttpStatus.INTERNAL_SERVER_ERROR_500) }
    }

    @Test
    fun `given a RuntimeException must get it status and handle the exception`() {
        val errorHandler = ErrorHandler()
        val classCastException = ClassCastException("")
        val context = mockk<Context>(relaxed = true)

        errorHandler.handleRuntimeException(classCastException, context)

        verify(exactly = 1) { context.status(HttpStatus.INTERNAL_SERVER_ERROR_500) }
    }

}