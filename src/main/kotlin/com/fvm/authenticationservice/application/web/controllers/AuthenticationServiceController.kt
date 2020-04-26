package com.fvm.authenticationservice.application.web.controllers

import com.fvm.authenticationservice.application.errors.HttpErrorResponse
import com.fvm.authenticationservice.domain.entities.AuthenticationRequest
import com.fvm.authenticationservice.domain.services.authentication.AuthenticationService
import io.javalin.http.Context
import io.javalin.plugin.openapi.annotations.OpenApi
import io.javalin.plugin.openapi.annotations.OpenApiContent
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody
import io.javalin.plugin.openapi.annotations.OpenApiResponse
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory

class AuthenticationServiceController(
    private val authenticationService: AuthenticationService
) {
    private val logger = LoggerFactory.getLogger(AuthenticationServiceController::class.java)

    @OpenApi(
        summary = "Authentication",
        operationId = "authentication",
        tags = ["AuthenticationService"],
        requestBody = OpenApiRequestBody( [OpenApiContent(AuthenticationRequest::class)],true, "Information to do the authentication"),
        responses = [
            OpenApiResponse(HttpStatus.OK_200.toString() , description = "valid authentication"),
            OpenApiResponse(HttpStatus.UNPROCESSABLE_ENTITY_422.toString(),
                [OpenApiContent(HttpErrorResponse::class)],
                "When validation fails, type property will be VALIDATION_EXCEPTION!")
        ]
    )
    fun authentication(ctx: Context) {
        val authenticationRequest:AuthenticationRequest = ctx.bodyValidator<AuthenticationRequest>().get()

        authenticationService.authentication(authenticationRequest)

        ctx.status(HttpStatus.OK_200)

    }


}

