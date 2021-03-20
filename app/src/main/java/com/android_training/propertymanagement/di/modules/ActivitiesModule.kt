package com.android_training.propertymanagement.di.modules

import androidx.lifecycle.ViewModel
import com.android_training.propertymanagement.di.ViewModelKey
import com.android_training.propertymanagement.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//Dependency for the Product ViewModel itself
@Module
abstract class ActivitiesModule {
    //Set ViewModels to the keys for factory creation
    @Binds
    @IntoMap //Muiltibinding =  Mapping into a key
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindProductViewModel(viewModel: HomeViewModel): ViewModel
}