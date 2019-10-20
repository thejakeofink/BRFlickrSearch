package com.thejakeofink.bleacherreportflickr.di

import dagger.Module
import javax.inject.Scope

@Module(
    includes = [
        DelegatesModule::class]
)
abstract class PolyAdapterConfigModule

@Module
abstract class DelegatesModule {
    // Bind delegates here.
}

@Scope
annotation class ActivityScope