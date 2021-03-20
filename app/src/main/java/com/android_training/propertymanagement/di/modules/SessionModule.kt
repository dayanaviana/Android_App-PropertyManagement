package com.android_training.propertymanagement.di.modules

import com.android_training.propertymanagement.app.SessionManager
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class SessionModule {

    @Provides
    fun provideSession(): SessionManager{
        return SessionManager()
    }
}
