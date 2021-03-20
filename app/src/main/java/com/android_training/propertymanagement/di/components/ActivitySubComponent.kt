package com.android_training.propertymanagement.di.components

import com.android_training.propertymanagement.di.LifecycleScope
import com.android_training.propertymanagement.di.modules.ActivitiesModule
import com.android_training.propertymanagement.ui.home.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

//@LifecycleScope
@Module
abstract class ActivityBuildersModule {
/*
    @Binds
    @IntoMap
    @ClassKey(HomeActivity::class)
    abstract bindAndroidInjectorFactory(
    ActivitySubComponent.Factory builder)

    @Subcomponent(modules = [ActivitiesModule::class])
    interface ActivitySubComponent : AndroidInjector<HomeActivity> {
        @Subcomponent.Factory
        interface Factory : AndroidInjector.Factory<HomeActivity>{}
    }

 */
}