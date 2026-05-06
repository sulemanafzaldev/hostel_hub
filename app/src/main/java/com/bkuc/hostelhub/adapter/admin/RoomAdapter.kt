package com.bkuc.hostelhub.adapter.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.databinding.ItemRoomCardBinding
import com.bkuc.hostelhub.model.RoomModel
import com.bumptech.glide.Glide // Highly recommended for loading images

class RoomAdapter(
    private var roomList: MutableList<RoomModel>,
    private val onEditClick: (RoomModel) -> Unit,
    private val onDeleteClick: (RoomModel, Int) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(val binding: ItemRoomCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]

        with(holder.binding) {
            tvRoomName.text = room.roomName
            tvRoomDetails.text = room.roomDetails

            // Load image using Glide or similar library
            if (room.imageUri != null) {
                Glide.with(ivRoomImage.context)
                    .load(room.imageUri)
                    .into(ivRoomImage)
            }

            // Click Listeners
            btnEdit.setOnClickListener { onEditClick(room) }
            btnDelete.setOnClickListener { onDeleteClick(room, position) }
        }
    }

    override fun getItemCount(): Int = roomList.size

    // Helper to refresh the count in the fragment
    fun updateList(newList: List<RoomModel>) {
        roomList.clear()
        roomList.addAll(newList)
        notifyDataSetChanged()
    }
}