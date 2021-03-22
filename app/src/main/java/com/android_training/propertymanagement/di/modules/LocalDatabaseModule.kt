package com.android_training.propertymanagement.di.modules


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android_training.propertymanagement.app.MyApplication
import com.android_training.propertymanagement.app.SessionManager
import com.android_training.propertymanagement.data.database.DaoProperty
import com.android_training.propertymanagement.data.database.MyDatabase
import com.android_training.propertymanagement.data.models.Property
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(): MyDatabase{
        return Room.databaseBuilder(
            MyApplication.appContext,
            MyDatabase::class.java,
            "local_db"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideDaoProperty(mydb: MyDatabase): DaoProperty{
        return mydb.getDaoProperty()
    }
}

