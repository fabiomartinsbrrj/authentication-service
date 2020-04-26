package com.fvm.authenticationservice.domain.services

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest

interface AuthenticationService{
    fun authentication(passwordRequest: AuthenticationRequest)
}
