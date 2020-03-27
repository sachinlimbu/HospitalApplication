package com.org.hospitalapplication.di.component

import com.org.hospitalapplication.MainActivity
import com.org.hospitalapplication.di.module.HospitalViewModuleModule
import com.org.hospitalapplication.di.scope.ActivityScope
import dagger.Component

@Component(modules = [HospitalViewModuleModule::class])
@ActivityScope
interface HospitalComponent {
    fun inject(mainActivity: MainActivity)
}