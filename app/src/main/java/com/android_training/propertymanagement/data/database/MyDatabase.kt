package com.android_training.propertymanagement.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android_training.propertymanagement.data.models.Property

@Database(entities = [Property::class], version = 1)
abstract class MyDatabase: RoomDatabase(){
    abstract fun getDaoProperty(): DaoProperty
}