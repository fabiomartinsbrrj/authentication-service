package com.fvm.authenticationservice.domain.exceptions

import com.fvm.authenticationservice.domain.exceptions.enum.DomainErrors

class ValidationException(message: String, cause: Throwable? = null) :
    DomainException(DomainErrors.VALIDATION_EXCEPTION, message, cause)
