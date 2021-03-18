package com.android_training.propertymanagement.di.components

import com.android_training.propertymanagement.di.modules.AppModule
import com.android_training.propertymanagement.ui.auth.LoginActivity
import com.android_training.propertymanagement.ui.auth.RegisterActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
}