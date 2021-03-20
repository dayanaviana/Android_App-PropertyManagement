package com.android_training.propertymanagement.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


class ViewModelFactory : ViewModelProvider.Factory{

    lateinit var mProviderMap: Map<Class<out ViewModel>, //Key
                                   Provider<ViewModel>>  //value
    @Inject
    constructor(
        providerMap: Map<Class<out ViewModel>, //Key
                          Provider<ViewModel>>  //value
    ){
        mProviderMap = providerMap
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mProviderMap[modelClass]!!.get() as T
    }

}