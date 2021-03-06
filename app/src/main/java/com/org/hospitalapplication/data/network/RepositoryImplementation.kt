package com.org.hospitalapplication.data.network

import android.app.Application
import android.util.Log
import com.org.hospitalapplication.R
import com.org.hospitalapplication.data.model.HospitalData
import io.reactivex.Single
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.nio.charset.Charset

class RepositoryImplementation(private val application: Application) :
    Repository {
    override fun getData(): Single<List<HospitalData>> {
        return Single.fromCallable { readHospitalData() }
    }

//    private lateinit var application: Application

    private fun readHospitalData() : List<HospitalData>{

        val hospitalList = ArrayList<HospitalData>()
        val inputStream: InputStream = application.resources.openRawResource(R.raw.hospital)
        val streamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
        val reader = BufferedReader(streamReader)
        var line: String

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
        return hospitalList
    }
}