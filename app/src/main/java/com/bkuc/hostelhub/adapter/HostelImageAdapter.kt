package com.bkuc.hostelhub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.R

class HostelImageAdapter(
    private val imageList: List<Int>
) : RecyclerView.Adapter<HostelImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.ivHostel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_hostel_images, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.image.setImageResource(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}