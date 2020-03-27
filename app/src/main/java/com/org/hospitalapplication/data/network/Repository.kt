package com.org.hospitalapplication.data.network

import com.org.hospitalapplication.data.model.HospitalData
import io.reactivex.Single

interface Repository {
    fun getData(): Single<List<HospitalData>>
}