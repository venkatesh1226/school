package com.ezyschooling.parenting.top_content.Adapter


import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Color.RED
import android.hardware.camera2.params.RggbChannelVector.RED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.red
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieProperty.COLOR
import com.ezyschooling.Agefilterinterface
import com.ezyschooling.R
import com.ezyschooling.parenting.ParentingHomeActivity
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.AgeFilter.AgeFiltersItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movie_item.view.*

class ImageAdapter( private val imageList: MutableList<AgeFiltersItem>,val inter:Agefilterinterface): RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    private lateinit var context: Context

    var clicked=0
    var prevholder: MyViewHolder?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context

        var itemView =
            LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get().load(imageList[position].thumbnail).into(holder.image)
        holder.txt_name.text = imageList[position].name

        holder.itemView.setOnClickListener {


            if(prevholder!=null) {

                if(holder!=prevholder) {

                   prevholder!!.txt_name.setTextColor(Color.BLACK)
                    prevholder!!.linearlayout.setBackgroundResource(R.drawable.filter_border)
                    inter.onclick(holder.txt_name.text.toString(),1)
                    holder.txt_name.setTextColor(Color.RED)
                    holder.linearlayout.setBackgroundResource(R.drawable.filter_border_red)
                    prevholder=holder

                }else{

                    prevholder!!.txt_name.setTextColor(Color.BLACK)
                    prevholder!!.linearlayout.setBackgroundResource(R.drawable.filter_border)
                    inter.onclick("",1)
                    prevholder=null
                }
            }else{

                inter.onclick(holder.txt_name.text.toString(),1)
                holder.linearlayout.setBackgroundResource(R.drawable.filter_border_red)
                holder.txt_name.setTextColor(Color.RED)
                prevholder=holder

            }

        }



            //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview


            // holder.txt_team.text = movieList[position].team
            // holder.txt_createdby.text = movieList[position].createdby
        }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var txt_name: TextView
        var linearlayout:LinearLayout
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

