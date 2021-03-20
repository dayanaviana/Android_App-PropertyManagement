package com.android_training.propertymanagement.data.repositories

import com.android_training.propertymanagement.data.network.PropertyApi
import com.android_training.propertymanagement.data.network.PropertyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PropertyRepository {
    suspend fun getPropertyList(): PropertyResponse {
             return PropertyApi.getApi().getPropertyList()
    }
}