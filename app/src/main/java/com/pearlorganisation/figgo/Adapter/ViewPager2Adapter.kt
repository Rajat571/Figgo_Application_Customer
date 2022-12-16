package com.pearlorganisation.figgo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.R

class ViewPager2Adapter(private val images:List<Int>):RecyclerView.Adapter<ViewPager2Adapter.ViewPageViewHolder>() {

  inner  class ViewPageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

      val imageView:ImageView = itemView.findViewById(R.id.ivimage)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2Adapter.ViewPageViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewPageViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewPager2Adapter.ViewPageViewHolder, position: Int) {
       val curImage = images[position]
        holder.imageView.setImageResource(curImage)
    }



    override fun getItemCount(): Int {
       return images.size
    }
}