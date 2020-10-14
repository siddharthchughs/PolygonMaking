package com.medical.mypolygonmap.Activities.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medical.mypolygonmap.Activities.ProjectAdapterManager.ProjectDataRecycler
import com.medical.mypolygonmap.Activities.ProjectModel.ProjectManagmentViewModel
import com.medical.mypolygonmap.Activities.model.ProjectManagment
import com.medical.mypolygonmap.R
import com.medical.mytestapp.LocalDbManager.DatabaseHelperManager

class ProjectFragment : Fragment() {

    private lateinit var subjectrecyclerview : RecyclerView
    private lateinit var subjectViewModel: ProjectManagmentViewModel
    private lateinit var subejctadapter: ProjectDataRecycler
    lateinit var db : DatabaseHelperManager
    private lateinit var editSearch: EditText
    private lateinit var filteredSearch:String
    private lateinit var imgClear: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = DatabaseHelperManager(requireContext())
        val view = inflater.inflate(R.layout.fragment_project, container, false)
       subjectrecyclerview = view.findViewById(R.id.subjectRecycler)
        editSearch = view.findViewById(R.id.editSearch)
        imgClear = view.findViewById(R.id.ic_clear)
        subjectViewModel = ViewModelProviders.of(this).get(ProjectManagmentViewModel::class.java)
        subjectViewModel.monsterData.observe(requireActivity(), Observer { it ->
            db.addListItem(it)
        })
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editSearch.text!= null) {
                    filteredSearch = editSearch.text.toString()
                    searchfrom(filteredSearch.toString())
                }
            }
            override fun afterTextChanged(s: Editable) {}
        })

        imgClear.setOnClickListener {
            editSearch.setText("")
        }
        return view
    }

    fun searchfrom(data:String){
        subejctadapter = ProjectDataRecycler(requireContext(), db.getSearch(data))
        subjectrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        subjectrecyclerview.adapter = subejctadapter
        subjectrecyclerview.setHasFixedSize(true)
        subejctadapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProjectFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}