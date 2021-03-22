package com.android_training.propertymanagement.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
data class Property(
    var __v: Int? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = 0,
    var _id: String? = null,
    var address: String? = "",
    var city: String? = "",
    var country: String? = "",
    var zipcode: String? = "",
    var image: String? = "",
    var latitude: String? = "",
    var longitude: String? = "",
    var mortageInfo: Boolean? = true,
    var propertyStatus: Boolean? = true,
    var purchasePrice: Double? = null,
    var state: String? = "",
    var userId: String? = null,
    var userType: String? = null
){
    fun getFullAddress(): String{
        return "$address, $city, $state, $country, $zipcode"
    }
    fun setLatLong(latLong: String){
        latitude = latLong.substringBefore(",",latLong)
        longitude = latLong.substringAfter(",",latLong)
    }
}