package com.softtek.citi.data.services.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.softtek.citi.AppConstants
import javax.inject.Inject

@SuppressLint("CommitPrefEdits")
class AppPreferences @Inject constructor(context: Context) {

    var pref: SharedPreferences =
        context.getSharedPreferences(AppConstants.SP_FILE, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(AppConstants.SP_FILE, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    inline fun <reified T> get(key: String, defaultValue: T): T {
        when (T::class) {
            Boolean::class -> return pref.getBoolean(key, defaultValue as Boolean) as T
            Float::class -> return pref.getFloat(key, defaultValue as Float) as T
            Int::class -> return pref.getInt(key, defaultValue as Int) as T
            Long::class -> return pref.getLong(key, defaultValue as Long) as T
            String::class -> return pref.getString(key, defaultValue as String) as T
            else -> {
                if (defaultValue is Set<*>) {
                    return pref.getString(key, (defaultValue as Set<String>).toString()) as T
                }
            }
        }
        return defaultValue
    }

    inline fun <reified T> put(key: String, value: T) {
        when (T::class) {
            Boolean::class -> editor.putBoolean(key, value as Boolean)
            Float::class -> editor.putFloat(key, value as Float)
            Int::class -> editor.putInt(key, value as Int)
            Long::class -> editor.putLong(key, value as Long)
            String::class -> editor.putString(key, value as String)
            else -> {
                if (value is Set<*>) {
                    editor.putString(key, (value as Set<*>).toString())
                }
            }
        }
        editor.commit()
    }

    fun onClear() {
        try {
            editor.clear()
            editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private var TAG = this::class.java.name
    }

}