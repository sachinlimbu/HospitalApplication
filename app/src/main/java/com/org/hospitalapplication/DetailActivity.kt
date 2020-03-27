package com.org.hospitalapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.org.hospitalapplication.data.model.HospitalData
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val hospital = intent.getParcelableExtra<HospitalData>(MAIN_ACTIVITY_TO_DETAIL_PASS)

        if(hospital == null){
            showError()
        }else{
            setHospitalDetails(hospital)
        }
    }
    private fun showError() {
        TODO("Not yet implemented")
    }

    private fun setHospitalDetails(hospital: HospitalData) {
        subtype_id.text = hospital.subType
        sector_id.text = hospital.sector
        organisationname_id.text = hospital.organisationName
        parent_name_id.text = hospital.parentName
        email_id.text = hospital.email
        phone_id.text = hospital.phone
        addressone_id.text = hospital.address1
        addresstwo_id.text = hospital.address2
        addressthree_id.text = hospital.address3
        postcode_id.text = hospital.postcode
    }
}
