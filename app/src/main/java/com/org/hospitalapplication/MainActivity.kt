package com.org.hospitalapplication

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.org.hospitalapplication.data.HospitalData
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var hospitalAdapter:HospitalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hospitalData = readHospitalData()
        recycler_view_hospital_main.layoutManager = LinearLayoutManager(this)

        hospitalAdapter = HospitalAdapter(hospitalData)
        recycler_view_hospital_main.adapter = hospitalAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main,menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchItem = menu?.findItem(R.id.action_search)

        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                hospitalAdapter.filter.filter(newText)
                return false
            }

        })

        return true
    }

    private fun readHospitalData() : MutableList<HospitalData>{

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
