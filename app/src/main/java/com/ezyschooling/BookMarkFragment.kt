package com.ezyschooling

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.api.RetrofitClient.instance1
import com.ezyschooling.parenting.top_content.Adapter.TopAdapter
import com.ezyschooling.parenting.top_content.Adapter.TopDiscussionsAdapter
import com.ezyschooling.parenting.top_content.Adapter.TopVideoAdapter
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result
import com.ezyschooling.userfavourite.resultArticles
import com.ezyschooling.userfavourite.resultDiscussions
import com.ezyschooling.userfavourite.resultVideos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookMarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookMarkFragment : Fragment() {
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

    lateinit var articleList: RecyclerView
    lateinit var videoList: RecyclerView
    lateinit var discussionList: RecyclerView
    lateinit var ctx: Context
    lateinit var noArticle: TextView
    lateinit var noVideo: TextView
    lateinit var noDiscussion: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_book_mark, container, false)
        init(view)
        api()
        return view
    }

    fun init(view:View){
        articleList=view.findViewById(R.id.articles_list)
        videoList=view.findViewById(R.id.videos_list)
        discussionList=view.findViewById(R.id.discussions_list)
        noArticle=view.findViewById(R.id.txt_no_articles)
        noVideo=view.findViewById(R.id.txt_no_videos)
        noDiscussion=view.findViewById(R.id.txt_no_discussions)
        ctx= context!!

    }

    fun api(){
        RetrofitClient.context=ctx
        instance1.bookmarkedArticles().enqueue(object: Callback<resultArticles>{
            override fun onFailure(call: Call<resultArticles>, t: Throwable) {
                Toast.makeText(ctx,"Connection Lost :(",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<resultArticles>, response: Response<resultArticles>) {
                if (response.isSuccessful)
                {
                    Toast.makeText(ctx,"Success",Toast.LENGTH_SHORT).show()
                    if(response.body()?.count!! >0){
                        noArticle.visibility=View.GONE
                        articleList.visibility=View.VISIBLE
                        response.body()?.results?.let { inflateArticle(it) }
                    }
                }
                else
                    Log.e("ERROR",response.message()+response.body().toString()+response.code())


            }
        })


        RetrofitClient.instance1.bookmarkedVideos().enqueue(object: Callback<resultVideos> {
            override fun onFailure(call: Call<resultVideos>, t: Throwable) {
                Toast.makeText(ctx,"Connection Lost :(", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<resultVideos>, response: Response<resultVideos>) {
                if(response.isSuccessful){
                    Toast.makeText(ctx,"Success"+response.body()?.count, Toast.LENGTH_SHORT).show()
                    if(response.body()?.count!! >0){
                        noVideo.visibility=View.GONE
                        videoList.visibility=View.VISIBLE
                        inflateVideos(response.body()!!.results)

                    }

                }
            }
        })

        RetrofitClient.instance1.bookmarkedDiscussions().enqueue(object: Callback<resultDiscussions> {
            override fun onFailure(call: Call<resultDiscussions>, t: Throwable) {
                Toast.makeText(ctx,"Connection Lost :(", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<resultDiscussions>,
                response: Response<resultDiscussions>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(ctx,"Success"+response.body()?.count, Toast.LENGTH_SHORT).show()
                    if(response.body()?.count!! >0){
                        noDiscussion.visibility=View.GONE
                        discussionList.visibility=View.VISIBLE
                        inflateDiscussions(response.body()!!.results)

                    }
                }
            }
        })
    }

    fun inflateArticle(list:Array<Result>){
        var articleAdapter: TopAdapter = TopAdapter(list as MutableList<Result>)
        articleList.adapter=articleAdapter
        articleList.layoutManager= LinearLayoutManager(ctx, LinearLayout.HORIZONTAL, false)

    }

    fun inflateVideos(list:Array<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.Result> ){
        var videosAdapter: TopVideoAdapter = TopVideoAdapter(list as MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.Result>)
        videoList.adapter=videosAdapter
        videoList.layoutManager= LinearLayoutManager(ctx, LinearLayout.HORIZONTAL, false)
    }

    fun inflateDiscussions(list:Array<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result>){
        var discussionsAdapter: TopDiscussionsAdapter = TopDiscussionsAdapter(list as MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result>)
        discussionList.adapter=discussionsAdapter
        discussionList.layoutManager= LinearLayoutManager(ctx, LinearLayout.HORIZONTAL, false)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookMarkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookMarkFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}