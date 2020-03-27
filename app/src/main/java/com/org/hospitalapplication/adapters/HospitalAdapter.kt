package com.org.hospitalapplication.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.org.hospitalapplication.R
import com.org.hospitalapplication.data.model.HospitalData
import kotlinx.android.synthetic.main.list_of_hospital.view.*
import kotlin.collections.ArrayList

class HospitalAdapter(
    private val hospitalList:MutableList<HospitalData>,
    private val clickListener: (HospitalData) -> Unit
) :RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>(),Filterable{

    private val hospitalListAll = ArrayList(hospitalList)

    private var onNothingFound: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_hospital,parent,false))
    }

    override fun getItemCount(): Int {
        return hospitalList.size
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val currentItem = hospitalList[position]
        holder.bind(currentItem)
    }

     inner class HospitalViewHolder(
         private val viewItem: View
     ): RecyclerView.ViewHolder(viewItem) {

        fun bind(hospital:HospitalData){
            viewItem.apply {
                organisationName_id.text = hospital.organisationName
                sector_id.text = hospital.sector

                setOnClickListener { clickListener(hospital) }
            }
        }
    }

    override fun getFilter(): Filter {
            return object: Filter(){
                private val filterResults = FilterResults()

                //run on background thread
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    hospitalList.clear()
                    if(constraint.isNullOrBlank()){
                        hospitalList.addAll(hospitalListAll)
                    }else{
                         for(item in hospitalListAll){
                             if(item.organisationName?.toLowerCase()?.contains(constraint.toString().toLowerCase())!!){
                                 hospitalList.add(item)
                             }
                         }
                    }
                    return filterResults.also {
                        it.values = hospitalList
                    }
                }
                //runs on ui thread
                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    if(hospitalList.isNullOrEmpty())
                        onNothingFound?.invoke()
                    notifyDataSetChanged()
                }
            }
    }
}

