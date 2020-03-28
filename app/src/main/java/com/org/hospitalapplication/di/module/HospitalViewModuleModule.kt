package com.org.hospitalapplication.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.org.hospitalapplication.MainActivity
import com.org.hospitalapplication.data.network.Repository
import com.org.hospitalapplication.data.network.RepositoryImplementation
import com.org.hospitalapplication.di.scope.ActivityScope
import com.org.hospitalapplication.viewmodels.HospitalViewModel
import com.org.hospitalapplication.viewmodels.HospitalsViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class HospitalViewModuleModule(private val mainActivity: MainActivity,private val application: Application) {

    @Provides
    @ActivityScope
    fun provideRepository():Repository{
        return RepositoryImplementation(application)
    }

    @Provides
    @ActivityScope
    fun provideHospitalViewModel(hospitalsViewModelFactory: HospitalsViewModelFactory):HospitalViewModel{
        return ViewModelProvider(mainActivity,hospitalsViewModelFactory).get(HospitalViewModel::class.java)
    }

    @Provides
    @ActivityScope
    fun provideHospitalViewModelFactory(repository: Repository):HospitalsViewModelFactory{
        return HospitalsViewModelFactory(repository)
    }

}