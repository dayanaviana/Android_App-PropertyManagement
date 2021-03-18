package com.android_training.propertymanagement.app

import android.app.Application
import com.android_training.propertymanagement.di.components.DaggerAppComponent
import com.android_training.propertymanagement.di.modules.AppModule

class MyApplication: Application() {
    var daggerComponent: DaggerAppComponent? = null
    override fun onCreate() {
        super.onCreate()

        daggerComponent = DaggerAppComponent.builder()
            .appModule(AppModule()).build() as DaggerAppComponent
    }
}