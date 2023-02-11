package com.figgo.customer.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.Model.CardOfferCashBackList
import com.figgo.customer.R

class CardofferAdpter(var context: RecyclerView, var cardcashbacklist: ArrayList<CardOfferCashBackList>): RecyclerView.Adapter<CardofferAdpter.CurrentofferHolder>() {

    class CurrentofferHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var tv_cashback=itemview.findViewById<TextView>(R.id.tv_cashback)
        var tv_booknow=itemview.findViewById<TextView>(R.id.tv_booknow)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardofferAdpter.CurrentofferHolder {
        return CardofferAdpter.CurrentofferHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: CardofferAdpter.CurrentofferHolder, position: Int) {
        var data=cardcashbacklist[position]
        holder.tv_cashback.text=data.tv_cashback
        holder.tv_booknow.text=data.tv_booknow
    }

    override fun getItemCount(): Int {
       return cardcashbacklist.size
    }
}



