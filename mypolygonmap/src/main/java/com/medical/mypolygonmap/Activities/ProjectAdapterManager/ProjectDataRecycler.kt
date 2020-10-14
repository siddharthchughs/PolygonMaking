package com.medical.mypolygonmap.Activities.ProjectAdapterManager

import android.content.Context
import android.text.format.DateFormat
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
           employeeDate.let {
//               val df = DateFormat()
  //             DateFormat.format("yyyy-MM-dd hh:mm:ss a", Date())
               it.text= monsterDataCollection.date
           }
           holder.itemView.setOnClickListener {
//               itemListener.onMonsteritemListener(monsterDataCollection)
           }
       }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.nameText)
        val employeeProject = itemView.findViewById<TextView>(R.id.projectName)
        val employeeDate = itemView.findViewById<TextView>(R.id.periodDate)
    }

    interface MonsterClickListener{
//        fun onMonsteritemListener(monster:Monster)
    }
}
