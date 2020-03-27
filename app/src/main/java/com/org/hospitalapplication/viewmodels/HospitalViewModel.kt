package com.org.hospitalapplication.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.org.hospitalapplication.R
import com.org.hospitalapplication.model.HospitalData
import io.reactivex.disposables.CompositeDisposable
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.nio.charset.Charset

class HospitalViewModel(application:Application) :AndroidViewModel(application) {

    private val context = application.applicationContext

    fun getHospitalList():ArrayList<HospitalData>{
        val hospitalList = ArrayList<HospitalData>()
        val inputStream: InputStream = context.resources.openRawResource(R.raw.hospital)
        val streamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
        val reader = BufferedReader(streamReader)
        var line: String

        try {
            while (reader.readLine() != null){
                line = reader.readLine().replace('\uFFFD',',')
                val hospitalDetails =  line.split(",").toTypedArray()
                val hospital = HospitalData(
                    hospitalDetails[0].toInt(),
                    hospitalDetails[1],
                    hospitalDetails[2],
                    hospitalDetails[3],
                    hospitalDetails[4],
                    hospitalDetails[5],
                    hospitalDetails[6],
                    hospitalDetails[7],
                    hospitalDetails[8],
                    hospitalDetails[9],
                    hospitalDetails[10],
                    hospitalDetails[11],
                    hospitalDetails[12],
                    hospitalDetails[13],
                    hospitalDetails[14],
                    hospitalDetails[15],
                    hospitalDetails[16],
                    hospitalDetails[17],
                    hospitalDetails[18],
                    hospitalDetails[19],
                    hospitalDetails[20],
                    hospitalDetails[21]
                )
                hospitalList.add(hospital)
                Log.i("MyActivity", "$hospital")
            }
        } catch (e: RuntimeException) {
            Log.e("MainActivity", "Error data was not able to read:" , e)
        }
        return hospitalList
    }
}