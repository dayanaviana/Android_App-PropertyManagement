package com.android_training.propertymanagement.di.modules

import androidx.lifecycle.ViewModelProvider
import com.android_training.propertymanagement.di.ViewModelFactory
import dagger.Binds
import dagger.Module

//Dependency for the ViewModelFactory
@Module
abstract class ViewModuleFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}