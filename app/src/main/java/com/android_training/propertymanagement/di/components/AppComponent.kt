package com.android_training.propertymanagement.di.components

import com.android_training.propertymanagement.data.repositories.PropertyRepository
import com.android_training.propertymanagement.di.modules.*
import com.android_training.propertymanagement.ui.auth.LoginActivity
import com.android_training.propertymanagement.ui.auth.LoginViewModel
import com.android_training.propertymanagement.ui.auth.RegisterActivity
import com.android_training.propertymanagement.ui.home.HomeActivity
import com.android_training.propertymanagement.ui.property.PropertyViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModuleFactoryModule::class,
    ActivitiesModule::class, SessionModule::class
    , LocalDatabaseModule::class
])
interface AppComponent {
    fun inject(activity: RegisterActivity)
    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: PropertyViewModel)
    fun inject(repo: PropertyRepository)
}