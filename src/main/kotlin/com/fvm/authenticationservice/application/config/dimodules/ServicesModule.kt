package com.fvm.authenticationservice.application.config.dimodules

import com.fvm.authenticationservice.domain.services.AuthenticationService
import com.fvm.authenticationservice.resources.services.AuthenticationServiceImpl
import org.koin.dsl.module.module

object ServicesModule {

    fun modules() = module {
        single<AuthenticationService> {
            AuthenticationServiceImpl()
        }
    }
}
