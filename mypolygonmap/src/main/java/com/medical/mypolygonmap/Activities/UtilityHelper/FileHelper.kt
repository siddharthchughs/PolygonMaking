package com.medical.mypolygonmap.Activities.UtilityHelper

import android.content.Context
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




    }

}