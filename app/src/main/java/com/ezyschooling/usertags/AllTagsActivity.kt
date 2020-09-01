package com.ezyschooling.usertags

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.R
import com.ezyschooling.api.RetrofitClient.instance1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllTagsActivity : AppCompatActivity() {
    lateinit var list:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tags)
        init()
    }
  fun   init(){
      list=findViewById(R.id.alltags_list)
      instance1.allTags().enqueue(object : Callback<resultTags>{
          override fun onFailure(call: Call<resultTags>, t: Throwable) {
              Toast.makeText(this@AllTagsActivity,"Connection Lost",Toast.LENGTH_SHORT).show()
          }

          override fun onResponse(call: Call<resultTags>, response: Response<resultTags>) {
              if (response.isSuccessful){
                  response.body()?.results?.let { populate(it) }
              }
              else
                  Log.e("Error",response.errorBody().toString()+response.message()+response.code())
          }
      })
  }

    fun populate(array:MutableList<Tag>){
        var adapter :TagAdapter= TagAdapter(this,"Follow")
        adapter.list=array
        list.adapter=adapter
        list.layoutManager= LinearLayoutManager(this)
    }

}