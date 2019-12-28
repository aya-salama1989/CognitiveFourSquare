package com.appfactory.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class SharedPreferencesHelper {

    private lateinit var editor: SharedPreferences.Editor


    fun isRealtime(context: Context, currency: Boolean) {
        editor = getCognitiveSharedPrefrences(context).edit()
        editor.putBoolean(REALTIME_UPDATE, currency)
        editor.commit()
    }

    fun getIsRealtime(context: Context): Boolean {
        return getCognitiveSharedPrefrences(context).getBoolean(REALTIME_UPDATE, true)
    }
}

fun getCognitiveSharedPrefrences(context: Context): SharedPreferences {
    return  context.getSharedPreferences(UPDATE_TYPE, MODE_PRIVATE)
}

const val UPDATE_TYPE = "update_type"
const val REALTIME_UPDATE = "realtime_update"