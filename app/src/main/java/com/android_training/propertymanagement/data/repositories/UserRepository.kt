package com.android_training.propertymanagement.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_training.propertymanagement.data.models.*
import com.android_training.propertymanagement.data.network.AuthApi
import com.android_training.propertymanagement.data.network.PropertyApi
import com.android_training.propertymanagement.data.network.PropertyResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class UserRepository {

    suspend fun login(user: User): AuthResponse? {
        var authResponse: AuthResponse? = null
        try {
            authResponse = AuthApi.getForRetrofit().postLogin_suspend(user)
        }catch (e: HttpException){
            var errorBody = e.response()?.errorBody()
            var type = object : TypeToken<AuthResponse>() {}.type
            var errorResponse = Gson().fromJson<AuthResponse>(errorBody!!.charStream(), type)
            var message = errorResponse.message //ex: password do not match
            Log.d("myApp", "UserRepository:login() Error: $message")
            authResponse = errorResponse
        }
        return authResponse
    }
    fun logout(){

    }
}
