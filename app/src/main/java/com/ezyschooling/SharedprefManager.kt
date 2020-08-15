package com.ezyschooling

import android.content.Context
import android.widget.Toast

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
    //Saving Id Of User
    fun saveId(id: String?){
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString("id", id)
        editor.apply()

    }
    fun id(): String? {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val id = sharedPreferences.getString("id", null)
        return id
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