package com.fvm.authenticationservice.application.web.controllers

import com.fvm.authenticationservice.application.web.controllers.AuthenticationServiceController
import com.fvm.authenticationservice.domain.entities.AuthenticationRequest
import com.fvm.authenticationservice.domain.services.AuthenticationService
import io.javalin.http.Context
import io.mockk.*
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Test

internal class AuthenticationServiceControllerTest {
    private val context = mockk<Context>(relaxed = true)
    private val passwordService = mockk<AuthenticationService>()
    private val passwordServiceController =
        AuthenticationServiceController(
            passwordService
        )

    @Test
    fun `given a valid json request must perform a password service with success`() {

        every { context.bodyValidator<AuthenticationRequest>().get() } returns AuthenticationRequest(
            "AbTp9!foo"
        )
        every { passwordService.authentication(any()) } just runs

        passwordServiceController.authentication(context)

        verify(exactly = 1) { context.status(HttpStatus.OK_200) }
    }

}