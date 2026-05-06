package com.bkuc.hostelhub.adapter.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.databinding.ItemDashboardCardBinding
import com.bkuc.hostelhub.model.DashboardItem

class DashboardAdapter(
    private val items: List<DashboardItem>,
    private val onItemClick: (DashboardItem) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    inner class DashboardViewHolder(val binding: ItemDashboardCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = ItemDashboardCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvLabel.text = item.title
            tvCount.text = item.count
            tvTrend.text = item.trend
            ivCardIcon.setImageResource(item.iconRes)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}