package com.example.retrofitapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.R
import kotlinx.android.synthetic.main.list_view.view.*

class RecyclerAdapter(
    var photoList: List<String>
) : RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {
    override fun getItemCount(): Int {
        return photoList.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_view, parent, false)
        )
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemView.apply {
            Glide.with(this)
                .load(photoList[position])
                .centerCrop()
                .into(imgDogPhoto)
        }
    }
    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}