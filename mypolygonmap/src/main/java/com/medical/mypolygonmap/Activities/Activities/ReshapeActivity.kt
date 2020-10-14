package com.medical.mypolygonmap.Activities.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.medical.mypolygonmap.Activities.Fragments.ProjectFragment
import com.medical.mypolygonmap.Activities.ProjectAdapterManager.ProjectDataRecycler
import com.medical.mypolygonmap.Activities.ProjectModel.ProjectManagmentViewModel
import com.medical.mypolygonmap.R

class ReshapeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reshape)
        showFragment()
    }

    fun showFragment(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val detailFragment = ProjectFragment()
        fragmentTransaction.add(R.id.container, detailFragment).commit()
    }
}