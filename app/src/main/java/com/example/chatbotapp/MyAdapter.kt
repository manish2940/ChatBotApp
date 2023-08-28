package com.example.chatbotapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(var context: Context, var list : ArrayList<Module>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        const val first_view = 1
        const val second_view = 2
    }

    private inner class MyView1Holder(itemView : View) :RecyclerView.ViewHolder(itemView) {

       val text1 = itemView.findViewById<TextView>(R.id.txt1)
       fun bind(position: Int){
           val recylerViewModel = list[position]
           text1.text = recylerViewModel.msg
       }
    }
    private inner class MyView2Holder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        val text2 = itemView.findViewById<TextView>(R.id.txt2)
        fun bind(position: Int){
            val recylerViewModel = list[position]
            text2.text = recylerViewModel.msg
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == first_view){
            return MyView1Holder(
                LayoutInflater.from(context).inflate(R.layout.eac_item2,parent,false)
            )

        }
        return MyView2Holder(
            LayoutInflater.from(context).inflate(R.layout.senderlayout,parent,false)
        )


    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        if(list[position].view_type == first_view){
            (holder as MyView1Holder).bind(position)
        }
        else{
            (holder as MyView2Holder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].view_type
    }



}
