package com.fvm.authenticationservice.resources.services

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest
import com.fvm.authenticationservice.domain.exceptions.ValidationException
import com.fvm.authenticationservice.domain.services.AuthenticationService

class AuthenticationServiceImpl :
    AuthenticationService {
    private val regex = Regex("^(?=.*[0..9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*\\W+).{9,}\$")
    private val detailsOfValidation = """
                                        |Nine or more characters
                                        |At least 1 digit
                                        |At least 1 lowercase letter
                                        |At least 1 upper letter
                                        |At least 1 special character
                """.trimMargin()

    override fun authentication(authenticationRequest: AuthenticationRequest)  {

        if (!regex.matches(authenticationRequest.password)) {
                throw ValidationException(
                    detailsOfValidation
                )
        }

    }

}
