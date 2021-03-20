package com.android_training.propertymanagement.di.components

import com.android_training.propertymanagement.di.modules.ActivitiesModule
import com.android_training.propertymanagement.di.modules.AppModule
import com.android_training.propertymanagement.di.modules.SessionModule
import com.android_training.propertymanagement.di.modules.ViewModuleFactoryModule
import com.android_training.propertymanagement.ui.auth.LoginActivity
import com.android_training.propertymanagement.ui.auth.LoginViewModel
import com.android_training.propertymanagement.ui.auth.RegisterActivity
import com.android_training.propertymanagement.ui.home.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModuleFactoryModule::class,
    ActivitiesModule::class, SessionModule::class])
interface AppComponent {
    fun inject(activity: RegisterActivity)
    fun inject(viewModel: LoginViewModel)
//    fun inject(activity: HomeActivity)
}