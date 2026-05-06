package com.bkuc.hostelhub.ui.admin.admindashboard.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.adapter.admin.RoomAdapter
import com.bkuc.hostelhub.databinding.FragmentRoomsBinding
import com.bkuc.hostelhub.model.RoomModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

class RoomsFragment : Fragment() {
    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!

    private lateinit var roomAdapter: RoomAdapter
    private val rooms = mutableListOf<RoomModel>()

    // For handling image selection from gallery
    private var selectedImageUri: Uri? = null
    private var currentDialogImage: ImageView? = null

    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                selectedImageUri = it
                currentDialogImage?.setImageURI(it)
                currentDialogImage?.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.btnAddRoom.setOnClickListener {
            showAddRoomDialog()
        }
    }

    private fun setupRecyclerView() {
        roomAdapter = RoomAdapter(rooms,
            onEditClick = { room ->
                Toast.makeText(context, "Edit: ${room.roomName}", Toast.LENGTH_SHORT).show()
            },
            onDeleteClick = { _, position ->
                rooms.removeAt(position)
                roomAdapter.notifyItemRemoved(position)
                updateRoomCount()
            }
        )

        binding.rvRooms.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = roomAdapter
            // Important for smooth scrolling inside NestedScrollView if you have one
            isNestedScrollingEnabled = false
        }

        updateRoomCount()
    }

    private fun showAddRoomDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_room, null)

        val ivSelectImage = dialogView.findViewById<ImageView>(R.id.ivSelectImage)
        val etName = dialogView.findViewById<TextInputEditText>(R.id.etName)
        val etSeater = dialogView.findViewById<TextInputEditText>(R.id.etSeater)
        val etPrice = dialogView.findViewById<TextInputEditText>(R.id.etPrice)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        currentDialogImage = ivSelectImage
        selectedImageUri = null // Reset for new entry

        ivSelectImage.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val seater = etSeater.text.toString()
            val price = etPrice.text.toString()

            if (name.isNotEmpty() && seater.isNotEmpty() && price.isNotEmpty()) {
                val newRoom = RoomModel(
                    id = System.currentTimeMillis().toString(),
                    roomName = name,
                    roomDetails = "$seater-seater · ₹$price/mo",
                    imageUri = selectedImageUri?.toString(),
                    isActive = true
                )

                rooms.add(newRoom)
                roomAdapter.notifyItemInserted(rooms.size - 1)
                updateRoomCount()
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.setContentView(dialogView)
        dialog.show()
    }

    private fun updateRoomCount() {
        binding.tvRoomCount.text = "${rooms.size} rooms total"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}