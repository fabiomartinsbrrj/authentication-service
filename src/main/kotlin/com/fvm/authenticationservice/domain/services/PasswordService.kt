package com.fvm.authenticationservice.domain.services

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest

interface PasswordService{
    fun validatePassword(authenticationRequest: AuthenticationRequest) : Boolean
}
