package com.bkuc.hostelhub.ui.admin.auth.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.adapter.FacilityAdapter
import com.bkuc.hostelhub.databinding.FragmentAddHostelBinding
import com.bkuc.hostelhub.ui.admin.admindashboard.AdminActivity
import com.bkuc.hostelhub.ui.user.MainActivity
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class AddHostelFragment : Fragment() {
    private var _binding: FragmentAddHostelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddHostelBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
            }
        }

        val facilitiesList =
            listOf("WiFi", "AC", "Mess", "Parking", "Laundry", "Hot Water", "Study Room", "Gym")
        val adapter = FacilityAdapter(facilitiesList)

        binding.rvFacilities.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = flexDirection
                justifyContent = JustifyContent.FLEX_START
            }
            this.adapter = adapter
        }
        binding.btnAddHostel.setOnClickListener {
            val selectedData = adapter.getSelectedFacilities()
            Log.d("SelectedFacilities", selectedData.toString())


            val intent = Intent(requireContext(), AdminActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }
}