package com.medical.mypolygonmap.Activities.ProjectAdapterManager

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.medical.mypolygonmap.Activities.model.ProjectManagment
import com.medical.mypolygonmap.R
import java.util.*


class ProjectDataRecycler(val context: Context, val monsterData: List<ProjectManagment>) :
    RecyclerView.Adapter<ProjectDataRecycler.ViewHolder>() {

    override fun getItemCount() = monsterData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val displayView = layoutInflater.inflate(R.layout.subjectpm_layout_items, parent, false)
        return ViewHolder(displayView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // to fetch the single item out of my collection of monster data at particular position
       val monsterDataCollection = monsterData[position]
       with(holder){
           nameText?.let {
               it.text = monsterDataCollection.employeename
           }
           employeeProject?.let {
            it.text =    monsterDataCollection.projectName
           }
           projectDate.let {
//               val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
               val outputFormat = SimpleDateFormat("yyyy-MM-dd")
               val date = outputFormat.parse(monsterDataCollection.date)
               val formattedDate = outputFormat.format(date)
               Log.e("the", "date is" + formattedDate)
       //       it.text= monsterDataCollection.date
              it.text= formattedDate.toString()
           }

           holder.itemView.setOnClickListener {
//               itemListener.onMonsteritemListener(monsterDataCollection)
           }
       }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.nameText)
        val employeeProject = itemView.findViewById<TextView>(R.id.projectName)
        val projectDate = itemView.findViewById<TextView>(R.id.projectDate)
        val timeSpent = itemView.findViewById<TextView>(R.id.periodTime)
    }

    interface MonsterClickListener{
//        fun onMonsteritemListener(monster:Monster)
    }
}
