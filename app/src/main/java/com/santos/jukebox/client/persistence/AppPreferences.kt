package com.santos.jukebox.client.persistence


import android.content.SharedPreferences

class AppPreferences(private val preferences: SharedPreferences) {


    fun saveBooleanVisibilityKey(key:String,value: Boolean){
        val edit = preferences.edit()
        edit.putBoolean(key,value)
        edit.apply()
    }


    fun getBooleanByKey(key:String) = preferences.getBoolean(key,false)

    companion object {
        const val NAME = "JuckBox"
        const val VISIBLITY_MUSIC_CLIENT = "VISIBLITY_MUSIC_CLIENT"
    }
}