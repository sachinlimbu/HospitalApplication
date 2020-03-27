package com.org.hospitalapplication.network

import com.org.hospitalapplication.model.HospitalData
import io.reactivex.Single

interface Repository {
    fun getData(): Single<List<HospitalData>>
}