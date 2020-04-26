package com.fvm.authenticationservice.domain.services

abstract class AuthenticationProcessor (val nextProcessor:AuthenticationProcessor?) {

    abstract fun validate(authenticationProvider: AuthenticationProvider)
}
