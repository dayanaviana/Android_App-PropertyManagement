package com.android_training.propertymanagement.app

import android.content.Context
import android.content.SharedPreferences

class SessionManager() {
    private val FILE_NAME = "session_file"
    private val KEY_USER_ID = "_id"
    private val KEY_NAME = "name"
    private val KEY_IS_LOGGED_IN = "isLoggedIn"

    private lateinit var mContext: Context
    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init{
        mContext = MyApplication.appContext
        sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun login(userId:String, name: String){
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_NAME,name)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.commit()
    }

    fun getUserName(): String?{
        return sharedPreferences.getString(KEY_NAME, null)
    }
    fun getUserId(): String?{
        return sharedPreferences.getString(KEY_USER_ID, null)
    }
    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    fun logout(){
//        editor.remove(KEY_IS_LOGGED_IN)
        editor.clear()
        editor.commit()
    }
}