package com.fvm.authenticationservice.application.config.dimodules

import com.fvm.authenticationservice.application.web.routes.AuthenticationServiceRoutes
import org.koin.dsl.module.module

object RoutesModule {

    fun modules() = module {
        single { AuthenticationServiceRoutes(get()) }
    }
}
