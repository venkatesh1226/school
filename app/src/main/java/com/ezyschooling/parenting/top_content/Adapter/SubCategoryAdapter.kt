package com.ezyschooling.parenting.top_content.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.Agefilterinterface
import com.ezyschooling.R
import com.ezyschooling.parenting.ParentingHomeActivity
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.AgeFilter.AgeFiltersItem
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movie_item.view.*

class SubCategoryAdapter(private val dataList: MutableList<AgeFiltersItem>,val inter: Agefilterinterface): RecyclerView.Adapter<SubCategoryAdapter.SubHolder>() {
    private lateinit var context: Context

    var prevholder: SubHolder? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubHolder {
        context = parent.context

        var itemView =
            LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false)
        return SubHolder(itemView)
    }

    override fun getItemCount(): Int {
return dataList.size   }

    override fun onBindViewHolder(holder: SubHolder, position: Int) {

        Picasso.get().load(dataList[position].thumbnail).into(holder.image)
        holder.txt_name.text = dataList[position].name
        holder.itemView.setOnClickListener {

          /*  inter.onclick(dataList[position].slug.toString(),2)

            holder.txt_name.setTextColor(Color.RED)

            if(prevholder!=null)
                prevholder!!.txt_name.setTextColor(Color.BLACK)

            prevholder=holder
            */
            if(prevholder!=null) {

                if(holder!=prevholder) {

                    prevholder!!.txt_name.setTextColor(Color.BLACK)
                    prevholder!!.linearlayout.setBackgroundResource(R.drawable.filter_border)
                    inter.onclick(dataList[position].slug.toString(),2)
                    holder.txt_name.setTextColor(Color.RED)
                    holder.linearlayout.setBackgroundResource(R.drawable.filter_border_red)
                    prevholder=holder

                }else{

                    prevholder!!.txt_name.setTextColor(Color.BLACK)
                    inter.onclick("",2)
                    prevholder!!.linearlayout.setBackgroundResource(R.drawable.filter_border)
                    prevholder=null
                }
            }else{

                inter.onclick(dataList[position].slug.toString(),2)
                holder.linearlayout.setBackgroundResource(R.drawable.filter_border_red)
                holder.txt_name.setTextColor(Color.RED)
                prevholder=holder

            }
/*
            holder.txt_name.setTextColor(Color.RED)
            val intent = Intent(context, ParentingHomeActivity::class.java)
            intent.putExtra("subCategory slug",dataList[position].slug)
            ContextCompat.startActivity(context, intent, Bundle())    */}
    }

    class SubHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        var image: ImageView
        var txt_name: TextView
        var linearlayout: LinearLayout
        //   var txt_team : TextView
        //  var txt_createdby : TextView

        init {
            image = itemView.image_movie
            txt_name = itemView.txt_name
            linearlayout=itemView.findViewById(R.id.linearlayout)
            //  txt_team = itemView.txt_team
            // txt_createdby = itemView.txt_createdby
        }

    }
}