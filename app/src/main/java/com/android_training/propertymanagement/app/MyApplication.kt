package com.android_training.propertymanagement.app

import android.app.Application
import android.content.Context
import com.android_training.propertymanagement.di.components.DaggerAppComponent
import com.android_training.propertymanagement.di.modules.AppModule

class MyApplication: Application() {
    var daggerComponent: DaggerAppComponent? = null
    override fun onCreate() {
        super.onCreate()
        MyApplication.appContext = applicationContext

        daggerComponent = DaggerAppComponent.builder()
            .appModule(AppModule()).build() as DaggerAppComponent
    }
    companion object{
        lateinit var appContext: Context
    }
}