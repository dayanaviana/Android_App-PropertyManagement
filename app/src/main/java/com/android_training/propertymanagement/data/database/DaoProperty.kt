package com.android_training.propertymanagement.data.database

import androidx.room.*
import com.android_training.propertymanagement.data.models.Property

@Dao
interface DaoProperty {
    @Insert
    fun addProperty(p: Property)

    @Update
    fun updateProperty(p: Property)

    @Delete
    fun deleteProperty(p: Property)

    @Query("select * from property")
    fun readData(): List<Property>

    @Query("select  * from property where _id = :id")
    fun getPropertyById(id: String): Property
}