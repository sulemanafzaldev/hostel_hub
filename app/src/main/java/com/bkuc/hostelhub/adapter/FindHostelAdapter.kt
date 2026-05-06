package com.bkuc.hostelhub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bkuc.hostelhub.databinding.LayoutBrowseRoomsBinding
import com.bkuc.hostelhub.model.FindHostel

class FindHostelAdapter(
    private var hostellist: List<FindHostel>,
    private val onFavClick: (FindHostel) -> Unit,
    private val onItemClick: (FindHostel) -> Unit,
) : RecyclerView.Adapter<FindHostelAdapter.HostelViewHolder>() {

    inner class HostelViewHolder(val binding: LayoutBrowseRoomsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostelViewHolder {
        val binding =
            LayoutBrowseRoomsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return HostelViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HostelViewHolder, position: Int) {
        val hostel = hostellist[position]

        with(holder.binding) {
            ivRoom.setImageResource(hostel.imageUrl)
            tvRating.text = hostel.rating
            tvSeaterTag.text = hostel.seaterTag
            tvTitle.text = hostel.title
            tvPrice.text = hostel.price
            tvLocation.text = hostel.location

            // Toggle Amenity Icons visibility
            iconWifi.visibility = if (hostel.hasWifi) View.VISIBLE else View.GONE
            iconAc.visibility = if (hostel.hasAc) View.VISIBLE else View.GONE
            iconBike.visibility = if (hostel.hasParking) View.VISIBLE else View.GONE

            // Handle Favorite Toggle
            // Note: You'll need a different drawable for selected state (e.g., ic_favorite_filled)
            // btnFav.setImageResource(if (hostel.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite)

            btnFav.setOnClickListener {
                hostel.isFavorite = !hostel.isFavorite
                onFavClick(hostel)
                notifyItemChanged(position)
            }
            root.setOnClickListener {
                onItemClick(hostel)
            }
        }
    }

    override fun getItemCount() = hostellist.size

    fun updateData(newsList:List<FindHostel>){
        this.hostellist = newsList
        notifyDataSetChanged()
    }

}