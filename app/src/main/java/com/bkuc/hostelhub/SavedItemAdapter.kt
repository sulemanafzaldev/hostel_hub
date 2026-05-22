package com.bkuc.hostelhub


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.databinding.LayoutSavedItemBinding
import com.bkuc.hostelhub.model.SaveItemModel

class SavedItemAdapter(
    private var itemList: List<SaveItemModel>,
    private val onHeartClick: (SaveItemModel) -> Unit // Heart icon click handle karne ke liye lambda
) : RecyclerView.Adapter<SavedItemAdapter.SavedItemViewHolder>() {

    // ViewHolder class jo binding ko hold karti hai
    inner class SavedItemViewHolder(val binding: LayoutSavedItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedItemViewHolder {
        val binding = LayoutSavedItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SavedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedItemViewHolder, position: Int) {
        val item = itemList[position]

        with(holder.binding) {
            // Data set kar rahe hain
            tvTitle.text = item.title
            tvLocation.text = item.location
            tvPrice.text = item.price
            tvRating.text = item.rating

            // Image load karne ke liye (Agar aap Glide use kar rahe hain)
            // Glide.with(root.context).load(item.imageUrl).into(ivPropertyImage)

            // Heart icon ki state set karne ke liye
            if (item.isFavorite) {
                ivHeartIcon.setImageResource(android.R.drawable.btn_star_big_on) // Aap apna red heart drawable lagayein
            } else {
                ivHeartIcon.setImageResource(android.R.drawable.btn_star_big_off) // Border wala heart drawable
            }

            // Click listener for Heart Icon
            ivHeartIcon.setOnClickListener {
                onHeartClick(item)
            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun updateData(newList: List<SaveItemModel>) {
        this.itemList = newList
        notifyDataSetChanged()
    }
}