package com.bkuc.hostelhub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.databinding.ItemOnboardingBinding
import com.bkuc.hostelhub.model.OnBoardingItem

class OnBoardingAdapter(
    private val list: List<OnBoardingItem>
) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    inner class OnBoardingViewHolder(val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding = ItemOnboardingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            imgOnBoarding.setImageResource(item.image)
            tvTitle.text = item.title
            tvSubTitle.text = item.subTitle
        }
    }
    override fun getItemCount(): Int = list.size
}
