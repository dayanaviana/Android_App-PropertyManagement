package com.android_training.propertymanagement.data.network

import com.android_training.propertymanagement.data.models.Property
import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PropertyApi {

    @GET("property")
    fun getPropertyFlowable() : Flowable<List<Property>>

    @GET("property")
    suspend fun getPropertyList() : PropertyResponse

    @GET("property/user/{id}")
    suspend fun getPropertyByUser(): Property

    companion object{
        //        operator fun invoke():AuthApi{
        fun getApi():PropertyApi{
            return Retrofit.Builder()
                .baseUrl("https://apolis-property-management.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PropertyApi::class.java)
        }
    }
}

data class PropertyResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val data: ArrayList<Property>,
    @SerializedName("error")
    val error: Boolean
)
