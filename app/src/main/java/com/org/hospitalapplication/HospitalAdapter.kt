package com.org.hospitalapplication
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.org.hospitalapplication.data.HospitalData
import kotlinx.android.synthetic.main.list_of_hospital.view.*
import kotlin.collections.ArrayList

class HospitalAdapter(
    private val hospitalList:MutableList<HospitalData>
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

        holder.organisationName.text = currentItem.organisationName
        holder.sector.text = currentItem.sector
    }

    class HospitalViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val organisationName: TextView = itemView.organisationName_id
        val sector: TextView = itemView.sector_id
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
                             if(item.organisationName.toLowerCase().contains(constraint.toString().toLowerCase())){
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

