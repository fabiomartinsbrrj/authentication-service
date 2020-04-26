package com.fvm.authenticationservice.application.config.dimodules

import com.fvm.authenticationservice.application.web.controllers.AuthenticationServiceController
import org.koin.dsl.module.module

object ControllersModule {

    fun modules() = module {
        single {
            AuthenticationServiceController(
                get()
            )
        }
    }
}

