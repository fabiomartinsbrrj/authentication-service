package com.fvm.authenticationservice.resources.services

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest
import com.fvm.authenticationservice.domain.services.authentication.AuthenticationService
import com.fvm.authenticationservice.domain.services.authentication.processor.AuthenticationProcessor
import com.fvm.authenticationservice.domain.services.authentication.provider.PasswordProvider

class AuthenticationServiceImpl(private val authProcessorChain:AuthenticationProcessor) : AuthenticationService {

    override fun authentication(authenticationRequest: AuthenticationRequest) =
        authProcessorChain.authentication(PasswordProvider(authenticationRequest))

}
