package com.org.hospitalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.org.hospitalapplication.adapters.HospitalAdapter
import com.org.hospitalapplication.model.HospitalData
import com.org.hospitalapplication.viewmodels.HospitalViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var hospitalAdapter: HospitalAdapter
    private lateinit var hospitalData:MutableList<HospitalData>

    private lateinit var hospitalViewModel:HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hospitalData = readHospitalData()

        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)

        hospitalViewModel.getHospitalList()

        hospitalViewModel.mHospitalDataList.observe(this, Observer {
            hospitalAdapter =
                HospitalAdapter(
                    hospitalData
                ) { hospitalData -> onHospitalClick(hospitalData) }
            recycler_view_hospital_main.adapter = hospitalAdapter
            recycler_view_hospital_main.layoutManager = LinearLayoutManager(this)
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main,menu)

//        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

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

    private fun onHospitalClick(detailPosition: HospitalData) {

        startActivity(
            Intent(this, DetailActivity::class.java).apply {
                putExtra(MAIN_ACTIVITY_TO_DETAIL_PASS, detailPosition)
            }
        )

    }
}