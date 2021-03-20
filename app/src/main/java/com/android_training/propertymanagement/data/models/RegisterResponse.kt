package com.android_training.propertymanagement.data.models

data class RegisterResponse(
    val data: User,
    val error: Boolean,
    val message: String
)
data class RegisterErrorResponse(
    val error: Boolean,
    val message: String
)