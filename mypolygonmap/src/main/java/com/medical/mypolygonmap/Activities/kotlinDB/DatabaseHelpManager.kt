package com.medical.mytestapp.LocalDbManager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.medical.mypolygonmap.Activities.model.ProjectManagment
import com.medical.mytestapp.LocalDbManager.DatabaseContracter.FeedEntry.COLUMN_SEARCH_DATE
import com.medical.mytestapp.LocalDbManager.DatabaseContracter.FeedEntry.COLUMN_SEARCH_ID
import com.medical.mytestapp.LocalDbManager.DatabaseContracter.FeedEntry.COLUMN_SEARCH_NAME
import com.medical.mytestapp.LocalDbManager.DatabaseContracter.FeedEntry.COLUMN_SEARCH_PROJECT
import com.medical.mytestapp.LocalDbManager.DatabaseContracter.FeedEntry.TABLE_SEARCH
import java.text.SimpleDateFormat
import java.util.*

class DatabaseHelperManager(context: Context?) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    var db: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_SEARCH)
    }

    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int, newVersion: Int
    ) {
        db.execSQL(SQL_DELETE_TABLE_SEARCH)
        onCreate(db)
    }

    override fun onDowngrade(
        db: SQLiteDatabase, oldVersion: Int, newVersion: Int
    ) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun addListItem(listItem: List<ProjectManagment>) {
        val db = this.writableDatabase
        val values = ContentValues()

        for (i in listItem.indices) {
            val inputFormat = android.icu.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val outputFormat = android.icu.text.SimpleDateFormat("yyyy-MM-dd")
            val date = inputFormat.parse(listItem.get(i).date)
            val formattedDate = outputFormat.format(date)
            Log.e("value inserting kotlin", "" + listItem.get(i).employeename + formattedDate)
            try {
                values.put(COLUMN_SEARCH_NAME, listItem.get(i).employeename)
                    values.put(COLUMN_SEARCH_ID, listItem.get(i).employeeid)
                    values.put(COLUMN_SEARCH_PROJECT, listItem.get(i).projectName)
                    values.put(COLUMN_SEARCH_DATE,formattedDate)
                    db.insert(TABLE_SEARCH, COLUMN_SEARCH_ID, values)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        db.close()
    }

    val getAllSearchData: List<ProjectManagment>
        get()
        {
            val db = this.readableDatabase
            val array_list = ArrayList<ProjectManagment>()
            val query = "SELECT * FROM " +TABLE_SEARCH
            val cursor = db.rawQuery(query, null)
                if ( cursor.moveToFirst() ||cursor.count>0) {
                        val dt = ProjectManagment()
                        dt.employeename = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_NAME))
                        dt.employeeid = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_ID))
                        dt.projectName = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_PROJECT))

                        Log.i("the", "data" + dt.projectName)
                        array_list.add(dt)

               }
            db.close()
            cursor.close()

            return array_list
        }


    fun getSearch(searchName: String): List<ProjectManagment> {
        val query_search = "SELECT DISTINCT * FROM "+ TABLE_SEARCH+ " WHERE "+ COLUMN_SEARCH_NAME +" LIKE "+ "'%"+searchName+"%' "+ "ORDER BY "+ COLUMN_SEARCH_DATE+" ASC"
        val db = readableDatabase
        val cursor = db.rawQuery(query_search, null)
        val search_list = ArrayList<ProjectManagment>()
           if (cursor.moveToFirst()) {
               run {
                   do {
                       val dt = ProjectManagment()
                       dt.employeename = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_NAME))
                       dt.date = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_DATE))
                       dt.projectName = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_PROJECT))
                       search_list.add(dt)
                       Log.i("the", "data" + dt)
                   } while (cursor.moveToNext())
               }
           } else {
               Log.i("the", "data doesn't exists")
           }
        db.close()
        cursor.close()
        return search_list
    }

    fun getSearchByMonth(monthName: String):List<ProjectManagment>{

        val query_search = "SELECT DISTINCT * FROM "+ TABLE_SEARCH+ " WHERE "+ COLUMN_SEARCH_DATE+"="+"'"+monthName+"'"
       Log.i("the ","query"+ query_search)
        val db = readableDatabase
        val cursor = db.rawQuery(query_search, null)
        val search_list = ArrayList<ProjectManagment>()
        if (cursor.moveToFirst()) {
            run {
                do {
                    val dt = ProjectManagment()
                    dt.employeename = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_NAME))
                    dt.date = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_DATE))
                    dt.projectName = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_PROJECT))
                    search_list.add(dt)
                    Log.i("the", "data" + dt)
                }while (cursor.moveToNext())
            }
        }
        db.close()
        cursor.close()
        return search_list
    }
//    fun getSearchbyMonthName(monthName: String):List<ProjectManagment>{
//
//        val query_search = "SELECT DISTINCT * FROM "+ TABLE_SEARCH+ " WHERE "+ COLUMN_SEARCH_DATE+"="+"'"+monthName+"'"
//       Log.i("the ","query"+ query_search)
//        val db = readableDatabase
//        val cursor = db.rawQuery(query_search, null)
//        val search_list = ArrayList<ProjectManagment>()
//        if (cursor.moveToFirst()) {
//            run {
//                do {
//                    val dt = ProjectManagment()
//                    dt.employeename = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_NAME))
//                    dt.date = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_DATE))
//                    dt.projectName = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH_PROJECT))
//                    search_list.add(dt)
//                    Log.i("the", "data" + dt)
//                }while (cursor.moveToNext())
//            }
//        }
//        db.close()
//        cursor.close()
//        return search_list
//    }



    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "SubjectDatabase.db"
        private const val SQL_CREATE_SEARCH =
            "CREATE TABLE " + TABLE_SEARCH + " (" +
                    COLUMN_SEARCH_ID + " TEXT," +
                    COLUMN_SEARCH_PROJECT + " TEXT," +
                    COLUMN_SEARCH_DATE + " TEXT," +
                    COLUMN_SEARCH_NAME + " TEXT)"

        private const val SQL_DELETE_TABLE_SEARCH =
            "DROP TABLE IF EXISTS " + TABLE_SEARCH
    }


}