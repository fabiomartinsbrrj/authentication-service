package com.fvm.authenticationservice.domain.services

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest
import com.fvm.authenticationservice.domain.exceptions.ValidationException
import java.util.Objects

class PasswordAuthenticationProcessor(nextProcessor:PasswordAuthenticationProcessor?) : AuthenticationProcessor(nextProcessor){
    private val regex = Regex("^(?=.*[0..9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*\\W+).{9,}\$")
    private val detailsOfValidation = """
                                        |Nine or more characters
                                        |At least 1 digit
                                        |At least 1 lowercase letter
                                        |At least 1 capital letter
                                        |At least 1 special character
                """.trimMargin()


    override fun validate(authenticationProvider: AuthenticationProvider)  {
        if (authenticationProvider is PasswordProvider) {
            val authenticationRequest:AuthenticationRequest = (authenticationProvider as PasswordProvider).authenticationRequest
            if (!regex.matches(authenticationRequest.password)) {
                throw ValidationException(detailsOfValidation)
            }
        } else if (!Objects.isNull(nextProcessor)) {
            nextProcessor?.validate(authenticationProvider)
        }
    }
}
