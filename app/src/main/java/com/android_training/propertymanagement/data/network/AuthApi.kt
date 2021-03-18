package com.android_training.propertymanagement.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_training.propertymanagement.data.models.*
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    fun postLogin(@Body user: User): Call<LoginResponse>

    //Retrofit
    @POST("auth/register")
    fun postRegister(@Body user: User): Call<RegisterResponse>

    //For Coroutines
    @POST("auth/register")
    suspend fun postRegisterCo(@Body user: User): RegisterResponse

    //For RxJava
    @POST("auth/register")
    fun postRegisterRx(@Body user: User): Single<RegisterResponse>

    @GET("users")
    fun getAllUsers(): Call<UsersResponse>

    companion object{
        //operator fun invoke():AuthApi{
        fun getForRetrofit():AuthApi{
            return Retrofit.Builder()
                .baseUrl("https://apolis-property-management.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApi::class.java)
        }
        fun getForRxJava():AuthApi{
            return Retrofit.Builder()
                .baseUrl("https://apolis-property-management.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(AuthApi::class.java)
        }
    }
}