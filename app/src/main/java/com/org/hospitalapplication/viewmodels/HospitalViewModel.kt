package com.org.hospitalapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.org.hospitalapplication.model.HospitalData
import io.reactivex.disposables.CompositeDisposable

class HospitalViewModel :ViewModel() {
    val mHospitalDataList = MutableLiveData<List<HospitalData>>()

    fun getHospitalList():LiveData<List<HospitalData>>{
        return mHospitalDataList
    }

}