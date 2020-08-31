package com.ezyschooling.userhome

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.Child.Child
import com.ezyschooling.Child.ChildResponse
import com.ezyschooling.R
import com.ezyschooling.SharedprefManager
import com.ezyschooling.api.RetrofitClient.instance
import com.ezyschooling.api.RetrofitClient.instance1
import com.ezyschooling.utils.Parents
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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


//creating UI*********************
lateinit var childList:RecyclerView
lateinit var noOfChild:TextView

var ctx:FragmentActivity? =null
    lateinit var parents:Parents


    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        ctx=activity

        var view:View= inflater.inflate(R.layout.fragment_home, container, false)
        childList=view.findViewById(R.id.child_list)
        noOfChild=view.findViewById(R.id.child_no)
        var context: Context? =activity?.applicationContext
        parents= context?.let { SharedprefManager.getInstance(it)?.getParents() }!!

        val id:Int?= context?.let { SharedprefManager.getInstance(it)?.getParents()?.user }
        if (id != null&&context!=null) {
            instance1.userChilds("$id").enqueue(
                object : Callback<ChildResponse> {
                    override fun onFailure(call: Call<ChildResponse>, t: Throwable) {

                        Toast.makeText(context, t.message + "Failed", Toast.LENGTH_LONG)
                            .show()
                        Log.i("Fail", t.message + "Failed")
                    }

                    override fun onResponse(call: Call<ChildResponse>, response: Response<ChildResponse>) {

                       if(response.isSuccessful){
                        val array:Array<Child>?=response.body()?.results


                           childList(array,context)

                           }
                        else
                           Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show()

                    }
                }
            )
        }




        return view;

    }
    fun childList(array: Array<Child>?,c:Context){
        val childAdapter:ChildAdapter= ChildAdapter(ctx)

        if(parents.gender=="Male")
            noOfChild.setText("Father of ${array?.size} children")
        else{

            noOfChild.setText("Mother of ${array?.size} children")}

        if (array != null) {

            childAdapter.list=array.toMutableList()
            childAdapter.notifyDataSetChanged()
        }

       // childAdapter.context=c
        childList.adapter=childAdapter

        childList.layoutManager=LinearLayoutManager(context)
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


