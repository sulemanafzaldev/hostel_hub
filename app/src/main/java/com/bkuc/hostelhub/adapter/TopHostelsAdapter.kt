package com.bkuc.hostelhub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.databinding.LayoutTopHostelsBinding // Ensure this matches your XML filename
import com.bkuc.hostelhub.model.TopHostel

class TopHostelsAdapter(
    private var hostelList: List<TopHostel>,
    private val onItemClick: (TopHostel) -> Unit
) : RecyclerView.Adapter<TopHostelsAdapter.HostelViewHolder>() {

    inner class HostelViewHolder(private val binding: LayoutTopHostelsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hostel: TopHostel) {
            binding.apply {
                // Set Data
                ivHostel.setImageResource(hostel.imageRes)
                tvTitle.text = hostel.title
                tvRating.text = hostel.rating
                tvLocation.text = hostel.location
                tvPrice.text = hostel.price
                tvSeater.text = hostel.seaterType

                // Click Listener
                root.setOnClickListener {
                    onItemClick(hostel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostelViewHolder {
        val binding = LayoutTopHostelsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HostelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HostelViewHolder, position: Int) {
        holder.bind(hostelList[position])
    }

    override fun getItemCount(): Int = hostelList.size

    // Inside TopHostelsAdapter class
    fun updateList(newList: List<TopHostel>) {
        this.hostelList = newList // Ensure 'hostelList' in your adapter is 'var' not 'val'
        notifyDataSetChanged()
    }
}