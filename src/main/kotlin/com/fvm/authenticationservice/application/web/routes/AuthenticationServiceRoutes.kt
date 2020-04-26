package com.fvm.authenticationservice.application.web.routes

import com.fvm.authenticationservice.application.web.controllers.AuthenticationServiceController
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post

class AuthenticationServiceRoutes(
    private val authenticationServiceController: AuthenticationServiceController
) {
    fun register() {
        path("/authentication") {
            post(authenticationServiceController::authentication)
        }
    }
}

