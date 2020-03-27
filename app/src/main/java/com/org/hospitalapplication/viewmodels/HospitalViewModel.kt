package com.org.hospitalapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.org.hospitalapplication.data.network.Repository
import com.org.hospitalapplication.data.model.HospitalData
import io.reactivex.disposables.CompositeDisposable

class HospitalViewModel (private val repository: Repository) :ViewModel() {
    val _mHospitalLoadingState = MutableLiveData<LoadingHospitalState>()


    private val compositeDisposable = CompositeDisposable()

    fun getHospitalData() {
        _mHospitalLoadingState.postValue(LoadingHospitalState.IN_PROGRESS)
        compositeDisposable.add(
            repository.getData()
                .subscribe({
                    if(it.isEmpty()){
                        _mHospitalLoadingState.value = LoadingHospitalState.ERROR("No Hospital found")
                    }else{
                        _mHospitalLoadingState.value = LoadingHospitalState.SUCCESS(it)
                    }
                }, {
                    _mHospitalLoadingState.value = LoadingHospitalState.ERROR(it.localizedMessage?:"Some error occurred")
                })
        )
    }

    sealed class LoadingHospitalState{
        object IN_PROGRESS:LoadingHospitalState()
        data class SUCCESS(val hospitalsData:List<HospitalData>):LoadingHospitalState()
        data class ERROR(val message:String):LoadingHospitalState()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}