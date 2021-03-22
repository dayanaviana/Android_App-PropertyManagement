package com.android_training.propertymanagement.data.network

import com.android_training.propertymanagement.data.models.Property
import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface PropertyApi {

    @GET("property")
    fun getPropertyFlowable() : Flowable<List<Property>>

    @GET("property")
    suspend fun getPropertyList() : PropertyListResponse

    @GET("property/user/{id}")
    suspend fun getPropertyByUser(usedId: String): PropertyListResponse

    @POST("property")
    suspend fun postProperty(@Body property: Property): PropertyResponse

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

data class PropertyListResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("data")
    val properties: ArrayList<Property>,
    @SerializedName("error")
    val error: Boolean
)
data class PropertyResponse(
    @SerializedName("data")
    val property: Property?,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)