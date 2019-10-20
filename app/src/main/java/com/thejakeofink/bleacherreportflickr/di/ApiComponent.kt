package com.thejakeofink.bleacherreportflickr.di

import dagger.Subcomponent
import javax.inject.Scope

@ApiScope
@Subcomponent(modules = [])
interface ApiComponent : BleacherReportAndroidInjectorComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ApiComponent
    }
}

@Scope
annotation class ApiScope