package com.fvm.authenticationservice

import com.fvm.authenticationservice.application.AuthenticationServiceApplication

object AuthenticationServiceApplicationMain {

    @JvmStatic
    fun main(args: Array<String>) {
        AuthenticationServiceApplication.run {
            init()
        }
    }
}

