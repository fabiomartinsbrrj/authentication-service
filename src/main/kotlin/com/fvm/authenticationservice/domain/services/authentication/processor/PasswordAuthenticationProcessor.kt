package com.fvm.authenticationservice.domain.services.authentication.processor

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest
import com.fvm.authenticationservice.domain.exceptions.ValidationException
import com.fvm.authenticationservice.domain.services.authentication.provider.AuthenticationProvider
import com.fvm.authenticationservice.domain.services.authentication.provider.PasswordProvider
import java.lang.IllegalArgumentException

class PasswordAuthenticationProcessor(nextProcessor:PasswordAuthenticationProcessor?) : AuthenticationProcessor(nextProcessor){
    private val regex = Regex("^(?=.*[0..9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*\\W+).{9,}\$")
    private val detailsOfValidation = """
                                        |Nine or more characters
                                        |At least 1 digit
                                        |At least 1 lowercase letter
                                        |At least 1 capital letter
                                        |At least 1 special character
                """.trimMargin()


    override fun execute(authenticationProvider: AuthenticationProvider)  {
        if (authenticationProvider is PasswordProvider) {
            val authenticationRequest:AuthenticationRequest = authenticationProvider.authenticationRequest
            if (!regex.matches(authenticationRequest.password)) {
                throw ValidationException(detailsOfValidation)
            }
        } else {
            throw IllegalArgumentException("The informed provider is not a PasswordProvider.")
        }
    }
}
