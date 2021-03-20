package com.android_training.propertymanagement.ui.auth

object RegisterValidation {

    private val existingUser = listOf("mark", "paul")

    fun validateLandlordRegistrationInput(
        email: String,
        password:String,
        confirmPassword: String,
        type: String
    ): Boolean{
        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || type.isEmpty())
            return false
        if(email in existingUser)
            return false
        if(password != confirmPassword)
            return false
        if(email.length < 6)
            return false

        return true
    }

}