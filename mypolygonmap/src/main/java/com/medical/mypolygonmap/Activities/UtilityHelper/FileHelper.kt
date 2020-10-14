package com.medical.mypolygonmap.Activities.UtilityHelper

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.widget.Toast

class FileHelper {

    companion object{
        fun getTextFromAssets(context:Context,filename:String):String{
            return context.assets.open(filename).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }


//   var dateFormat = "2020-09-01T08:00:00.000Z"
//        var formatter = SimpleDateFormat("yyyy MM dd", Locale.ENGLISH)
//        var mDate = formatter.parse(dateFormat).toString()
//        Log.e("the", "date is" + dateFormat)

//        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        //     val date: Date = sdf.parse(dateFormat)
//        ///  sdf.setTimeZone(TimeZone.getTimeZone("IST"))
//        // System.out.println(sdf.format(date))
//        //  Log.e("the", "date is" + dateFormat)
//
//        //  val sdf = SimpleDateFormat("dd-MM-yyyy")
//        //    val timeonly = SimpleDateFormat("hh:mm")
//        //  val dateString = sdf.format(dateFormat)
//        //  val timeString = timeonly.format(dateFormat)
//        //show present date
//        //show present date
//
//        //
//        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
//        val date = inputFormat.parse("2018-04-10T04:00:00.000Z")
//        val formattedDate = outputFormat.format(date)


    }

}