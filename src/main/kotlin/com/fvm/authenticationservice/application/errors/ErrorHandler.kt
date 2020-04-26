package com.fvm.authenticationservice.application.errors

import com.fvm.authenticationservice.domain.exceptions.DomainException
import com.fvm.authenticationservice.domain.exceptions.ValidationException
import com.fvm.authenticationservice.domain.exceptions.enum.DomainErrors
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.HttpResponseException
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory

class ErrorHandler {
    private val logger = LoggerFactory.getLogger(ErrorHandler::class.java)

    fun handleException(e: Throwable, ctx: Context) = handle(e, e.message, ctx)

    fun handleRuntimeException(e: RuntimeException, ctx: Context) = handle(e, e.message, ctx)


    private fun handle(e: Any, message:String?, ctx: Context) {
        logger.debug("handling ${e::class.simpleName}: ${e}", e )

        when (e) {
            is DomainException -> HttpErrorResponse(
                e.type,
                message
            )
            is ValidationException -> HttpErrorResponse(
                e.type,
                message = message
            )
            is BadRequestResponse -> HttpErrorResponse(
                DomainErrors.BAD_REQUEST_RESPONSE,
                message
            )
            else -> HttpErrorResponse(
                DomainErrors.INTERNAL_ERROR,
                message
            )
        }.also {
            ctx.status(getHttpStatus(e))
            ctx.json(it)
        }
    }

    private fun getHttpStatus(error: Any) = when (error) {
        is HttpResponseException -> HttpStatus.BAD_REQUEST_400
        is ValidationException -> HttpStatus.UNPROCESSABLE_ENTITY_422
        else -> HttpStatus.INTERNAL_SERVER_ERROR_500
    }
}
