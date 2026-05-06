package com.bkuc.hostelhub.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.databinding.ItemFacilityChipBinding

class FacilityAdapter(private val facilities: List<String>) :
    RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {

    private val selectedItems = HashSet<String>()

    inner class FacilityViewHolder(val binding: ItemFacilityChipBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val binding = ItemFacilityChipBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FacilityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.binding.tvFacilityName.text = facility

        val isSelected = selectedItems.contains(facility)

        if (isSelected) {
            holder.binding.tvFacilityName.setBackgroundResource(R.drawable.chip_selected_bg)
            holder.binding.tvFacilityName.setTextColor(Color.WHITE)
        } else {
            holder.binding.tvFacilityName.setBackgroundResource(R.drawable.chip_bg)
            holder.binding.tvFacilityName.setTextColor(Color.BLACK)
        }

        holder.itemView.setOnClickListener {
            if (selectedItems.contains(facility)) {
                selectedItems.remove(facility)
            } else {
                selectedItems.add(facility)
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = facilities.size

    fun getSelectedFacilities(): List<String> {
        return selectedItems.toList()
    }
}