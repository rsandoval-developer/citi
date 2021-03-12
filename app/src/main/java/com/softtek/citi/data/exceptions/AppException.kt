package com.softtek.citi.data.exceptions

class AppException @JvmOverloads constructor(
    val validationType: Type,
    message: String = "Validation error.",
    cause: Throwable? = null
) : Throwable(message, cause) {

    enum class Type {
        EMAIL_VALIDATION_ERROR,
        EMAIL_IS_EMPTY_ERROR,
        PASSWORD_IS_EMPTY_ERROR,
        EMAIL_PASSWORD_IS_EMPTY_ERROR,
        ERROR_NETWORK
    }
}