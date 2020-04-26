package com.fvm.authenticationservice.domain.services

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest


class PasswordProvider(val authenticationRequest: AuthenticationRequest) : AuthenticationProvider {

}
