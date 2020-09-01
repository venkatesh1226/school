package com.ezyschooling.usertags

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.R
import com.ezyschooling.api.RetrofitClient.instance1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TagFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TagFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var allTags:Button
    lateinit var list:RecyclerView
    var contexts:Context?=getContext()
     var ctx:  FragmentActivity? =activity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_tag, container, false)
        init(view)
        api()
        allTags.setOnClickListener(){
            val intent:Intent=Intent(ctx,AllTagsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    fun init(v:View){
        allTags=v.findViewById(R.id.all_tags)
        list=v.findViewById(R.id.tags_list)
        contexts= context
        ctx=activity
    }
    fun api(){
        instance1.followedTags().enqueue(object: Callback<resultTags>
        {
            override fun onFailure(call: Call<resultTags>, t: Throwable) {
                Toast.makeText(context,"Connection Lost",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<resultTags>, response: Response<resultTags>) {
               if(response.isSuccessful){
                   response.body()?.results?.let { createList(it) }
               }

            }
        })
    }
    fun createList(array:MutableList<Tag>){
        var adapter :TagAdapter= TagAdapter(ctx,"Unfollow")
        adapter.list=array
        list.adapter=adapter
        list.layoutManager=LinearLayoutManager(contexts)

    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TagFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TagFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}