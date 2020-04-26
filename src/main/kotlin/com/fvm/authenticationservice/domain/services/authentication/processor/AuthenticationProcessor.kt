package com.fvm.authenticationservice.domain.services.authentication.processor

import com.fvm.authenticationservice.domain.services.authentication.provider.AuthenticationProvider

abstract class AuthenticationProcessor (private val nextProcessor: AuthenticationProcessor?) {

    fun authentication(authenticationProvider: AuthenticationProvider) {
        execute(authenticationProvider)

        nextProcessor?.authentication(authenticationProvider)
    }

    protected abstract fun execute(authenticationProvider: AuthenticationProvider)

}
