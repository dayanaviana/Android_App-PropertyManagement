package com.android_training.propertymanagement.data.models

data class UsersResponse(
    val count: Int,
    val data: ArrayList<User>,
    val error: Boolean
)