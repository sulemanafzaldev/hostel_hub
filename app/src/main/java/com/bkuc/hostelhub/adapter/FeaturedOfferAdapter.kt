package com.bkuc.hostelhub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.model.FeaturedOffer

class FeaturedOfferAdapter(
    private val list: List<FeaturedOffer>
) : RecyclerView.Adapter<FeaturedOfferAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvOffer: TextView = itemView.findViewById(R.id.tvOffer)
        val tvBadge: TextView = itemView.findViewById(R.id.tvBadge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_featured_offer, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvTitle.text = item.title
        holder.tvSubtitle.text = item.subtitle
        holder.tvDescription.text = item.description
        holder.tvOffer.text = item.offerPercent
        holder.tvBadge.text = item.badgeText
    }
}