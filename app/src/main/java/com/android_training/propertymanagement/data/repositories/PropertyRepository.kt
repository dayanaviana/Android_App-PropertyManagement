package com.android_training.propertymanagement.data.repositories

import android.util.Log
import com.android_training.propertymanagement.app.SessionManager
import com.android_training.propertymanagement.data.database.DaoProperty
import com.android_training.propertymanagement.data.models.Property
import com.android_training.propertymanagement.data.network.PropertyApi
import com.android_training.propertymanagement.data.network.PropertyListResponse
import com.android_training.propertymanagement.data.network.PropertyResponse
import com.android_training.propertymanagement.di.components.DaggerAppComponent
import com.android_training.propertymanagement.di.modules.LocalDatabaseModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PropertyRepository() {
    @Inject lateinit var daoProperty: DaoProperty
    init {
        Log.d("myApp", "PropertyRepository init()")
        DaggerAppComponent.builder()
            .localDatabaseModule(LocalDatabaseModule()).build()
            .inject(this)
    }

    suspend fun getPropertyList(): PropertyListResponse {
             return PropertyApi.getApi().getPropertyList()
    }
    suspend fun getPropertyByUser(userId: String): PropertyListResponse {
        return PropertyApi.getApi().getPropertyByUser(userId)
    }
    suspend fun postProperty(property: Property): PropertyResponse{
        var response =  PropertyApi.getApi().postProperty(property)
        Log.d("myApp", "PropertyRepository addOnApi:${response.message}")
        var dbResponse = response.property?.let { daoProperty.addProperty(it)}
        Log.d("myApp", "PropertyRepository addLocalDb:$dbResponse")
        return response
    }
}