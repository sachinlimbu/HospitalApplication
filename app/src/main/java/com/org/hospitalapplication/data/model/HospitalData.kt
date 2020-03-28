package com.org.hospitalapplication.data.model

import android.os.Parcelable
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HospitalData(
    val organisationID : Int,
    val organisationCode : String? = null,
    val organisationType : String? = null,
    val subType : String? = null,
    val sector : String? = null,
    val organisationStatus : String? = null,
    val isPimsManaged : String? = null,
    val organisationName : String? = null,
    val address1 : String? = null,
    val address2 : String? = null,
    val address3 : String? = null,
    val city : String? = null,
    val county : String? = null,
    val postcode : String? = null,
    val latitude : String? = null,
    val longitude : String? = null,
    val parentODSCode : String? = null,
    val parentName : String? = null,
    val phone : String? = null,
    val email : String? = null,
    val website : String? = null,
    val fax : String? = null
):Parcelable