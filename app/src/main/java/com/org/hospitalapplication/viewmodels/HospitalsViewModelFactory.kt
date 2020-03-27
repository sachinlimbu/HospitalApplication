package com.org.hospitalapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.org.hospitalapplication.network.Repository


@Suppress("UNCHECKED_CAST")
class HospitalsViewModelFactory (private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HospitalViewModel(repository) as T
    }

}