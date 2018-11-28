package com.kartik.grevocab.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import wipro.wiprotest.R
import wipro.wiprotest.model.Rows


import java.util.ArrayList

class LandingRecyclerAdapter(internal var items: ArrayList<Rows>?, val con:Context?) :
    RecyclerView.Adapter<LandingRecyclerAdapter.ViewHolder>() {


    //Cursor data;
    internal var clickListener: OnItemClickListener? = null

    fun clearItems(){
        items?.clear()
    }

    override fun getItemCount(): Int {

        return items?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val t = items?.get(position)
        holder.itemView.tag=t?.title
        //holder.progress.text = "(" + t?.description + " of " + t?.description + " completed" + ")"
        holder.name.text = t?.title
        holder.description.text = t?.description
        Picasso
            .with(con)
            .load(t?.imageHref)
            .placeholder(R.drawable.placeholder)
            .fit()
            .centerCrop()// to avoid a stretched image
            .into(holder.image);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_rows, parent, false)
        return ViewHolder(v)
    }


    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    //for future if you might want to make some click events
    fun setOnItemClickLickListener(listener: OnItemClickListener) {

        clickListener = listener
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnClickListener {

        internal var name: TextView
        internal var image: ImageView
        internal var description: TextView

        init {
            name = itemView.findViewById(R.id.Title)
            image = itemView.findViewById(R.id.Image)
            description = itemView.findViewById(R.id.Description)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            clickListener?.onItemClick(v, this.adapterPosition) //OnItemClickListener mItemClickListener;
        }

    }

}
