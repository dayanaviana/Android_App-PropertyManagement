package com.android_training.propertymanagement

import com.android_training.propertymanagement.ui.auth.RegisterValidation
import org.junit.Test
import com.google.common.truth.Truth.assertThat;

class RegisterValidationTest {
    @Test
    fun `empty fields return false`(){
        var result = RegisterValidation.validateLandlordRegistrationInput(
            "","","","")
        assertThat(result).isFalse()
    }
    @Test
    fun `empty email return false`(){
        var result = RegisterValidation.validateLandlordRegistrationInput(
            "","password","password","landlord")
        assertThat(result).isFalse()
    }
    @Test
    fun `short email return false`(){
        var result = RegisterValidation.validateLandlordRegistrationInput(
            "d@u.c","password","password","landlord")
        assertThat(result).isFalse()
    }
    @Test
    fun `empty password return false`(){
        var result = RegisterValidation.validateLandlordRegistrationInput(
            "dayana@gmail.com","","password","landlord")
        assertThat(result).isFalse()
    }
    @Test
    fun `different password and confirmation return false`(){
        var result = RegisterValidation.validateLandlordRegistrationInput(
            "dayana@gmail.com","password1","password","landlord")
        assertThat(result).isFalse()
    }
}