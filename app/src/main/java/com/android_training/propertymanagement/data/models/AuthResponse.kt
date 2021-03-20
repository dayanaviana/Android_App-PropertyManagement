package com.android_training.propertymanagement.data.models

data class AuthResponse(
    val token: String? = null,
    val data: User?,
    val error: Boolean?,
    val message: String?
)