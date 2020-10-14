package com.medical.mypolygonmap.Activities.ProjectModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.medical.mypolygonmap.Activities.Repository.ProjectManageRepository
import com.medical.mypolygonmap.Activities.model.ProjectManagment


class ProjectManagmentViewModel(app:Application) : AndroidViewModel(app) {
    val repoValue = ProjectManageRepository(app)
    val monsterData = repoValue.mutableList

}
