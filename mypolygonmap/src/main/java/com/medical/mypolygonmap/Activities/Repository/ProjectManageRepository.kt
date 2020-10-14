package com.medical.mypolygonmap.Activities.Repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.medical.mypolygonmap.Activities.UtilityHelper.FileHelper
import com.medical.mypolygonmap.Activities.model.ProjectManagment
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProjectManageRepository(val app: Application) {
    val mutableList = MutableLiveData<List<ProjectManagment>>()
    val listType = Types.newParameterizedType(List::class.java,ProjectManagment::class.java)

    init {
        getProjectManagemtData()
     }

    fun getProjectManagemtData(){
        val text = FileHelper.getTextFromAssets(app,"employeemanage.json")
        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<ProjectManagment>> = moshi.adapter(listType)
        mutableList.value = adapter.fromJson(text)?: emptyList()
    }
}