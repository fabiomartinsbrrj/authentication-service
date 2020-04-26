package com.fvm.authenticationservice.application.config.dimodules

import com.fvm.authenticationservice.domain.services.authentication.AuthenticationService
import com.fvm.authenticationservice.domain.services.authentication.processor.AuthenticationProcessor
import com.fvm.authenticationservice.domain.services.authentication.processor.PasswordAuthenticationProcessor
import com.fvm.authenticationservice.resources.services.AuthenticationServiceImpl
import org.koin.dsl.module.module

object ServicesModule {

    fun modules() = module {
        single<AuthenticationService> {
            AuthenticationServiceImpl(getChainOfAuthProcessor())
        }
    }

    private fun getChainOfAuthProcessor(): AuthenticationProcessor = PasswordAuthenticationProcessor(null)
}
