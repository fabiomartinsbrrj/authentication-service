package com.fvm.authenticationservice.resources.services

import com.fvm.authenticationservice.domain.entities.AuthenticationRequest
import com.fvm.authenticationservice.domain.exceptions.ValidationException
import com.fvm.authenticationservice.domain.exceptions.enum.DomainErrors
import com.fvm.authenticationservice.resources.services.AuthenticationServiceImpl
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

class AuthenticationServiceImplTest  {


    @Test
    fun `given a valid password must perform successfully`() {
        val password =
            AuthenticationRequest("AbTp9!foo")

        AuthenticationServiceImpl().authentication(password)

        assertTrue(true)
    }

    @Test
    fun `given a invalid password must throw a ValidationException with type VALIDATION_EXCEPTION`() {
        val password = AuthenticationRequest("A")

        val exception: ValidationException = assertThrows<ValidationException>  {
            AuthenticationServiceImpl().authentication(password)
        }

        assertTrue(exception.type== DomainErrors.VALIDATION_EXCEPTION)
    }

    @Test
    fun `given a list of invalid passwords must perform successfully`() {
        val passwordRequests = listOf(
            AuthenticationRequest(""),
            AuthenticationRequest("aa"),
            AuthenticationRequest("aB"),
            AuthenticationRequest("AAAbbbCc")
        )

        assertAll("Must fail at all",
            {
                passwordRequests.forEach {
                    val exception: ValidationException = assertThrows<ValidationException> {
                        AuthenticationServiceImpl().authentication(it)
                    }
                    assertEquals(exception.type , DomainErrors.VALIDATION_EXCEPTION)
                }

            }
        )

    }
    
}
