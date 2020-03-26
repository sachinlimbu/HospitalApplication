package com.org.hospitalapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.org.hospitalapplication.data.HospitalData
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hospitalData = readHospitalData()
        recycler_view_hospital_main.layoutManager = LinearLayoutManager(this)
        recycler_view_hospital_main.adapter = HospitalAdapter(hospitalData)
    }

    private fun readHospitalData() : List<HospitalData>{

        val hospitalList = ArrayList<HospitalData>()
        val inputStream: InputStream = resources.openRawResource(R.raw.hospital)
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
