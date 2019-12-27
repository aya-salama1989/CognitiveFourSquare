package com.appfactory.Utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class SharedPreferencesHelper {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun isRealtime(context: Context, currency: Boolean) {
        sharedPreferences =
            context.getSharedPreferences(UPDATE_TYPE, MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putBoolean(REALTIME_UPDATE, currency)
        editor.commit()
    }

    fun getIsRealtime(context: Context): Boolean {
        sharedPreferences =
            context.getSharedPreferences(UPDATE_TYPE, MODE_PRIVATE)
        return sharedPreferences.getBoolean(REALTIME_UPDATE, true)
    }
}

const val UPDATE_TYPE = "update_type"
const val REALTIME_UPDATE = "realtime_update"