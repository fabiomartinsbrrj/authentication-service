package com.fvm.authenticationservice.application.errors

import com.fvm.authenticationservice.domain.exceptions.enum.DomainErrors

data class HttpErrorResponse(
    val type: DomainErrors = DomainErrors.VALIDATION_EXCEPTION,
    val message: String?
)

