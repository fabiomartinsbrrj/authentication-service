package com.fvm.authenticationservice.application.config.dimodules

import com.fvm.authenticationservice.application.errors.ErrorHandler
import org.koin.dsl.module.module

object ConfigModule {

    fun modules() = module {
        single { ErrorHandler() }
    }
}
