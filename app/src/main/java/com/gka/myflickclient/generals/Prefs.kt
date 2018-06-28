package com.gka.myflickclient.generals

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Prefs(context: Context) {
    private val _appPref = "app_moderator_prefs"
    private val _history = "history"
    private val gson = Gson()
    private val prefs: SharedPreferences = context.getSharedPreferences(_appPref, Context.MODE_PRIVATE)


    private val turnsType = object : TypeToken<ArrayList<String>>() {}.type

    var searchHistory: ArrayList<String>
        get() = gson.fromJson(prefs.getString(_history, ""), turnsType) ?: arrayListOf()
        set(value) = prefs.edit().putString(_history, gson.toJson(value)).apply()


}