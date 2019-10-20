package com.thejakeofink.bleacherreportflickr.di

import com.thejakeofink.bleacherreportflickr.PhotoDelegate
import com.thejakeofink.bleacherreportflickr.model.PhotoModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import polyadapter.PolyAdapter
import javax.inject.Scope

@Module(
    includes = [
        DelegatesModule::class]
)
abstract class PolyAdapterConfigModule

@Module
abstract class DelegatesModule {
    @Binds
    @IntoMap
    @ClassKey(PhotoModel::class)
    abstract fun photoDelegate(impl: PhotoDelegate):
            PolyAdapter.BindingDelegate<*, *>
}

@Scope
annotation class ActivityScope