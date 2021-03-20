package com.android_training.propertymanagement.data.models

import java.io.Serializable

data class LoginResponse(
    val token: String? = null,
    val user: User? = null
)
data class LoginResponseError(
    val error: Boolean? = null,
    val message: String? = null
)
