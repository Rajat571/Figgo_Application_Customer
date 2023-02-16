package com.figgo.customer.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.figgo.customer.Model.CurrentWalletModel
import com.figgo.customer.R

class CurrentWalletAdapter(var context: Context, var walletdata: ArrayList<CurrentWalletModel>): RecyclerView.Adapter<CurrentWalletAdapter.CurrentWalletHolder>(){

    class CurrentWalletHolder (itemview: View): RecyclerView.ViewHolder(itemview){

        var tv_datewallet = itemview.findViewById<TextView>(R.id.tv_datewallet)
        var tv_pay_amount = itemview.findViewById<TextView>(R.id.tv_pay_amount)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentWalletAdapter.CurrentWalletHolder {
        return CurrentWalletAdapter.CurrentWalletHolder(LayoutInflater.from(parent.context).inflate(R.layout.currentwalletlistscreen, parent, false))
    }

    override fun onBindViewHolder(holder: CurrentWalletAdapter.CurrentWalletHolder, position: Int) {
        var data = walletdata[position]

        holder.tv_datewallet.text = data.tv_datewallet
        holder.tv_pay_amount.text = data.tv_pay_amount
    }

    override fun getItemCount(): Int {
       return walletdata.size
    }


}