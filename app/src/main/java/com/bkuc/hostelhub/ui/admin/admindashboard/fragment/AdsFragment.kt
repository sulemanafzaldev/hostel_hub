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
import com.bkuc.hostelhub.adapter.admin.AdAdapter
import com.bkuc.hostelhub.databinding.FragmentAdsBinding
import com.bkuc.hostelhub.model.AdModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

class AdsFragment : Fragment() {

    private var _binding: FragmentAdsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adAdapter: AdAdapter
    private val adsList = mutableListOf<AdModel>()

    private var selectedAdImageUri: Uri? = null
    private var adDialogImage: ImageView? = null

    // Image Picker Launcher
    private val adImagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedAdImageUri = it
            adDialogImage?.setImageURI(it)
            adDialogImage?.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdsRecyclerView()

        // Button to open Add Ad Dialog
        binding.btnAddRoom.setOnClickListener {
            showAddAdDialog()
        }
    }

    private fun setupAdsRecyclerView() {
        adAdapter = AdAdapter(adsList,
            onStatusChanged = { ad, isChecked ->
                val status = if (isChecked) "Enabled" else "Disabled"
                Toast.makeText(context, "${ad.title} is now $status", Toast.LENGTH_SHORT).show()
            },
            onDeleteClick = { _, position ->
                adsList.removeAt(position)
                adAdapter.notifyItemRemoved(position)
                updateAdsCount()
            }
        )

        binding.rvRooms.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adAdapter
        }

        updateAdsCount()
    }

    private fun showAddAdDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_ad, null)

        val ivBanner = dialogView.findViewById<ImageView>(R.id.ivSelectAdBanner)
        val etTitle = dialogView.findViewById<TextInputEditText>(R.id.etAdTitle)
        val etSubtitle = dialogView.findViewById<TextInputEditText>(R.id.etAdSubtitle)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSaveAd)

        adDialogImage = ivBanner
        selectedAdImageUri = null

        ivBanner.setOnClickListener {
            adImagePickerLauncher.launch("image/*")
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val subtitle = etSubtitle.text.toString().trim()

            if (title.isNotEmpty() && subtitle.isNotEmpty() && selectedAdImageUri != null) {
                val newAd = AdModel(
                    id = System.currentTimeMillis().toString(),
                    title = title,
                    subtitle = subtitle,
                    imageUrl = selectedAdImageUri.toString(),
                    isActive = true
                )

                adsList.add(newAd)
                adAdapter.notifyItemInserted(adsList.size - 1)
                updateAdsCount()
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Please enter all details and select an image", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.setContentView(dialogView)
        dialog.show()
    }

    private fun updateAdsCount() {
        binding.tvRoomCount.text = "${adsList.size} ads total"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}