package com.android_training.propertymanagement.data.models

import java.io.Serializable

data class User(
//    val __v: Int? = 0,
    val email: String? = null,
    val password: String? = null,
    val _id: String? = null,
    val createdAt: String? = null,
    val type: String? = null,
    val name: String? = null,
    val landlordEmail: String? = null
): Serializable {
    companion object{
        const val DATA = "USER"
    }
}

data class UserToRegister(
    val email: String? = null,
    val password: String? = null,
    val type: String? = null,
    val name: String? = null
)