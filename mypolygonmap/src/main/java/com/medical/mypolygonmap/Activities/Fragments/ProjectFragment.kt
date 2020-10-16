package com.medical.mypolygonmap.Activities.Fragments

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medical.mypolygonmap.Activities.ProjectAdapterManager.ProjectDataRecycler
import com.medical.mypolygonmap.Activities.ProjectModel.ProjectManagmentViewModel
import com.medical.mypolygonmap.R
import com.medical.mytestapp.LocalDbManager.DatabaseHelperManager
import java.util.*


class ProjectFragment : Fragment() , View.OnClickListener{

    private lateinit var subjectrecyclerview : RecyclerView
    private lateinit var subjectViewModel: ProjectManagmentViewModel
    private lateinit var subejctadapter: ProjectDataRecycler
    lateinit var db : DatabaseHelperManager
    private lateinit var editSearch: EditText
    private lateinit var filteredSearch:String
    private lateinit var imgClear: ImageView
    private lateinit var imgFilter: ImageView
    private lateinit var monthSelected: TextView
    private lateinit var msg:String
    private lateinit var mw:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = DatabaseHelperManager(requireContext())
        val view = inflater.inflate(R.layout.fragment_project, container, false)
        subjectrecyclerview = view.findViewById(R.id.subjectRecycler)
        editSearch = view.findViewById(R.id.editSearch)
        imgClear = view.findViewById(R.id.ic_clear)
        imgFilter = view.findViewById(R.id.ic_filter)
        monthSelected = view.findViewById(R.id.monthName)
        subjectViewModel = ViewModelProviders.of(this).get(ProjectManagmentViewModel::class.java)
        subjectViewModel.monsterData.observe(requireActivity(), Observer { it ->
            db.addListItem(it)
        })

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editSearch.text != null) {
                    filteredSearch = editSearch.text.toString()
                    searchfrom(filteredSearch.toString())
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        imgClear.setOnClickListener {
            editSearch.setText("")
        }
        imgFilter.setOnClickListener(this)
        return view
    }

    fun searchfrom(data: String){
        subejctadapter = ProjectDataRecycler(requireContext(), db.getSearch(data))
        subjectrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        subjectrecyclerview.adapter = subejctadapter
        subjectrecyclerview.setHasFixedSize(true)
        subejctadapter.notifyDataSetChanged()

    }
    fun searchMonthDate(data: String){
        subejctadapter = ProjectDataRecycler(requireContext(), db.getSearchByMonth(data))
        subjectrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        subjectrecyclerview.adapter = subejctadapter
        subjectrecyclerview.setHasFixedSize(true)
        subejctadapter.notifyDataSetChanged()


    }
/*    fun searchMonthby(data: String){
        subejctadapter = ProjectDataRecycler(requireContext(), db.getSearchbyMonthName(data))
        subjectrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        subjectrecyclerview.adapter = subejctadapter
        subjectrecyclerview.setHasFixedSize(true)
        subejctadapter.notifyDataSetChanged()

    }*/

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProjectFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClick(view: View?) {

        val id = view?.id
        when(id) {
            R.id.ic_filter -> {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val dpd = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { view, year: Int, month, dayOfMonth -> // Display Selected date in textbox
                        mw = (month + 1).toString()
                        if (month < 10) {
                            msg = "$year-0$mw-0$dayOfMonth"
                            Log.i("the option one", "date" + msg)
                        }
                        if (day < 10) {
                            msg = "$year-0$mw-0$dayOfMonth"
                        }
                        if (day >= 10) {
                            msg = "$year-0$mw-$dayOfMonth"
                        }
                        if (month < 10 && day > 10) {
                            msg = "$year-0$mw-$dayOfMonth"
                        }
                        searchMonthDate(msg)
                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }
        }
    }
}