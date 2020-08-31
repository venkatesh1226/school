package com.ezyschooling

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import com.ezyschooling.utils.Parents as Parents

class SharedprefManager(private val mCtx: Context) {
    val sharedPreferences = mCtx.getSharedPreferences(
        SHARED_PREF_NAME,
        Context.MODE_PRIVATE
    )
    fun saveToken(key: String?) {

        val editor = sharedPreferences.edit()
        editor.putString("key", key)
        editor.apply()
    }


    fun saveParents(parent: Parents){
        val editor=sharedPreferences.edit()
        val gson: Gson =Gson()
        val json:String =gson.toJson(parent)

        editor.putString("Parents",json)
        editor.commit()
    }
    fun getParents():Parents{
        val gson:Gson=Gson()
        val json:String?=sharedPreferences.getString("Parents",null)
        val parents:Parents=gson.fromJson(json,Parents::class.java)
       return parents }



    fun token(): String {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val key = sharedPreferences.getString("key", null)
        return "Token $key"
    }

    val isLoggedin: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME,
                Context.MODE_PRIVATE
            )
            return if (sharedPreferences.getString("key", null) != null) true else false
        }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()

        editor.clear().apply()
        Toast.makeText(mCtx,"Cleareed"+SharedprefManager.getInstance(mCtx)?.token(),Toast.LENGTH_SHORT).show()
//        editor.clear()
//        editor.apply()
    }


    companion object {
        private const val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedprefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedprefManager? {
            if (mInstance == null) {
                mInstance =
                    SharedprefManager(mCtx)
            }
            return mInstance
        }


    }

}