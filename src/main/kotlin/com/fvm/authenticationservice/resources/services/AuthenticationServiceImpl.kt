package com.fvm.authenticationservice.resources.services

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest
import com.fvm.authenticationservice.domain.services.AuthenticationProcessor
import com.fvm.authenticationservice.domain.services.AuthenticationService
import com.fvm.authenticationservice.domain.services.PasswordAuthenticationProcessor
import com.fvm.authenticationservice.domain.services.PasswordProvider

class AuthenticationServiceImpl :
    AuthenticationService {

    private fun getChainOfAuthProcessor(): AuthenticationProcessor? {
        return PasswordAuthenticationProcessor(null)
    }

    override fun authentication(authenticationRequest: AuthenticationRequest) {
        val authProcessorChain = getChainOfAuthProcessor()

        authProcessorChain?.validate(PasswordProvider(authenticationRequest))
    }

}
