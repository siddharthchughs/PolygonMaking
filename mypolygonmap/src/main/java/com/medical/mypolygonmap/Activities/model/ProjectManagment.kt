package com.medical.mypolygonmap.Activities.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ProjectManagment(
    @Json(name="employeeID")var employeeid: String,
    @Json (name= "employeeName")var employeename: String,
    @Json(name="projectName")var projectName: String,
    @Json(name="date")var date: String
//    val scariness: Int
){
    constructor() : this("", "", "", "")
}
