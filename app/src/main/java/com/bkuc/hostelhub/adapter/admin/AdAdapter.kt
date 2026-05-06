package com.bkuc.hostelhub.adapter.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.databinding.ItemAdsCardBinding
import com.bkuc.hostelhub.model.AdModel
import com.bumptech.glide.Glide

class AdAdapter(
    private val adList: MutableList<AdModel>,
    private val onStatusChanged: (AdModel, Boolean) -> Unit,
    private val onDeleteClick: (AdModel, Int) -> Unit
) : RecyclerView.Adapter<AdAdapter.AdViewHolder>() {

    inner class AdViewHolder(val binding: ItemAdsCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val binding = ItemAdsCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        val ad = adList[position]

        with(holder.binding) {
            tvAdTitle.text = ad.title
            tvAdSubtitle.text = ad.subtitle
            switchAdStatus.isChecked = ad.isActive

            // Update "Active/Inactive" text label based on switch
            tvStatusLabel.text = if (ad.isActive) "Active" else "Inactive"

            // Load Banner Image
            Glide.with(ivAdBanner.context)
                .load(ad.imageUrl)
                .placeholder(R.drawable.image_offer_card)
                .into(ivAdBanner)

            // Toggle Listener
            switchAdStatus.setOnCheckedChangeListener { _, isChecked ->
                ad.isActive = isChecked
                tvStatusLabel.text = if (isChecked) "Active" else "Inactive"
                onStatusChanged(ad, isChecked)
            }

            // Delete Listener
            btnDeleteAd.setOnClickListener {
                onDeleteClick(ad, holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = adList.size
}