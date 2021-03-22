package com.android_training.propertymanagement.app

import com.android_training.propertymanagement.data.models.AuthResponse
import com.android_training.propertymanagement.data.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException

class Utils {
    companion object{
        fun getErrorMessage(e: HttpException): String? {
            val errorBody = e.response()?.errorBody()
            val type = object : TypeToken<ErrorResponse>() {}.type
            val errorResponse = Gson().fromJson<ErrorResponse>(errorBody!!.charStream(), type)
            val message = errorResponse.message //ex: password do not match
            return message
        }
    }
}
enum class MyActions{SUCCESS, FAILURE}

data class ErrorResponse(
    val error: Boolean?,
    val message: String?
)