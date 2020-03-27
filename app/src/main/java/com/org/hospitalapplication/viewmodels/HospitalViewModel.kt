package com.org.hospitalapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.org.hospitalapplication.data.network.Repository
import com.org.hospitalapplication.data.model.HospitalData
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HospitalViewModel (private val repository: Repository) :ViewModel() {
    private val _mHospitalDataList = MutableLiveData<List<HospitalData>>()
    val mHospitalDataList:LiveData<List<HospitalData>>
        get() = _mHospitalDataList

    private val compositeDisposable = CompositeDisposable()

    fun getHospitalData() {

        compositeDisposable.add(
            repository.getData()
                .subscribe({
                    _mHospitalDataList.value = it
                }, {})
        )
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}