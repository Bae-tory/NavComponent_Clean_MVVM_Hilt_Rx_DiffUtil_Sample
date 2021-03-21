package com.baetory.domain.exception

internal class NoParamsException(message: String = "params never be null for this use case.") : Exception(message)