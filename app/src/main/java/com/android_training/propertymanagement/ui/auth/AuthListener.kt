package com.android_training.propertymanagement.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onStarted()
    fun onSuccess(liveLoginResponse: LiveData<String>)
    fun onFailure(message: String)
}