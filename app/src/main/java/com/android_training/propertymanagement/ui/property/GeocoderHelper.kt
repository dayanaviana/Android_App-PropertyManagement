package com.android_training.propertymanagement.ui.property

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.widget.Toast
import com.android_training.propertymanagement.app.MyApplication
import java.io.IOException


class GeocoderHelper() {
    private lateinit var geocoder: Geocoder
    private lateinit var mContext: Context
    init {
        mContext = MyApplication.appContext
        geocoder = Geocoder(mContext)
    }
    fun getLatLongFromAddress(address: String):String?{
        var message: String? = null
        try {
            val addresses: List<Address>? =
                geocoder.getFromLocationName(address, 1)
            if (addresses != null && !addresses.isEmpty()) {
                val address: Address = addresses[0]
                // Use the address as needed
                message = java.lang.String.format(
                    "%f,%f",address.getLatitude(), address.getLongitude()
                )
            } else {
                // Display appropriate message when Geocoder services are not available
                Toast.makeText(mContext, "Unable to geocode zipcode", Toast.LENGTH_LONG).show()
            }
        } catch (e: IOException) {
            Toast.makeText(mContext, "Unable to geocode zipcode", Toast.LENGTH_LONG).show()
        }
        return message
    }
}