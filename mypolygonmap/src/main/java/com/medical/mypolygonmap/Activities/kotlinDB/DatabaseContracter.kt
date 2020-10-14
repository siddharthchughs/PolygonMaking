package com.medical.mytestapp.LocalDbManager

/**
 * Created :: Siddharth
 */

import android.provider.BaseColumns

class DatabaseContracter{
    object FeedEntry : BaseColumns {
    const val TABLE_SEARCH = "SEARCH_PROJECT_MANAGMENT"
        const val COLUMN_SEARCH_NAME = "employeeName"
        const val COLUMN_SEARCH_ID = "employeeID"
       const val COLUMN_SEARCH_PROJECT = "projectName"
        const val COLUMN_SEARCH_DATE = "date"
    }
}
