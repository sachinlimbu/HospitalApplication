package com.org.hospitalapplication
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.org.hospitalapplication.data.HospitalData
import kotlinx.android.synthetic.main.list_of_hospital.view.*

class HospitalAdapter(private val hospitalList:List<HospitalData>) :RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_hospital,parent,false))
    }

    override fun getItemCount(): Int {
        return hospitalList.size
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val currentItem = hospitalList[position]

        holder.organisationName.text = currentItem.organisationName
        holder.sector.text = currentItem.sector
    }

    class HospitalViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val organisationName: TextView = itemView.organisationName_id
        val sector: TextView = itemView.sector_id
    }
}

