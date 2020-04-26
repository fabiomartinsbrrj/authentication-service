package com.fvm.authenticationservice.domain.exceptions

import com.fvm.authenticationservice.domain.exceptions.enum.DomainErrors

abstract class DomainException(
    val type: DomainErrors = DomainErrors.INTERNAL_ERROR,
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)

