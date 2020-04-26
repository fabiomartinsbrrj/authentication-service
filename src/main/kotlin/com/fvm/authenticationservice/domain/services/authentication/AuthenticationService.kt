package com.fvm.authenticationservice.domain.services.authentication

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest

interface AuthenticationService{
    fun authentication(authenticationRequest: AuthenticationRequest)
}
