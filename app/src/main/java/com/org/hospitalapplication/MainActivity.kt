package com.org.hospitalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.org.hospitalapplication.adapters.HospitalAdapter
import com.org.hospitalapplication.model.HospitalData
import com.org.hospitalapplication.viewmodels.HospitalViewModel
import com.org.hospitalapplication.viewmodels.HospitalsViewModelFactory
import com.org.hospitalapplication.network.RepositoryImplementation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var hospitalAdapter: HospitalAdapter

    private lateinit var hospitalViewModel: HospitalViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hospitalViewModel = ViewModelProvider(
            this,
            HospitalsViewModelFactory(
                RepositoryImplementation(
                    application
                )
            )
        ).get(HospitalViewModel::class.java)


        hospitalViewModel.mHospitalDataList.observe(this, Observer {
            hospitalAdapter =
                HospitalAdapter(
                    it.toMutableList()
                ) { hospitalData -> onHospitalClick(hospitalData) }
            recycler_view_hospital_main.adapter = hospitalAdapter
            recycler_view_hospital_main.layoutManager = LinearLayoutManager(this)
        })

        hospitalViewModel.getHospitalData()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

//        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchItem = menu?.findItem(R.id.action_search)

        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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


    private fun onHospitalClick(detailPosition: HospitalData) {

        startActivity(
            Intent(this, DetailActivity::class.java).apply {
                putExtra(MAIN_ACTIVITY_TO_DETAIL_PASS, detailPosition)
            }
        )

    }

}