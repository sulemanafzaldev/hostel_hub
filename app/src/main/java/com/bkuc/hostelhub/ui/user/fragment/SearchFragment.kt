package com.bkuc.hostelhub.ui.user.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.adapter.FindHostelAdapter
import com.bkuc.hostelhub.databinding.FragmentSearchBinding
import com.bkuc.hostelhub.model.FindHostel
import com.bkuc.hostelhub.utils.StatusBar

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var hostelAdapter: FindHostelAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        StatusBar.changeStatusBarColor(requireActivity(), R.color.white)

        setupRecyclerView()
        loadDummyData()
    }


    private fun setupRecyclerView() {
        hostelAdapter = FindHostelAdapter(hostellist = emptyList(),
            onFavClick = { hostel ->
                val status = if (hostel.isFavorite)
                    "Added to Favorite" else
                    "Remove From Favorite"
                Toast.makeText(context, "${hostel.title} : $status", Toast.LENGTH_SHORT).show()
            },
            onItemClick = { hostel ->
                Toast.makeText(context, "Clicked: ${hostel.title}", Toast.LENGTH_SHORT).show()
                // Navigate to Detail Fragment here if needed
            }
        )
        binding.rvFindHostels.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = hostelAdapter
        }
    }


    private fun loadDummyData() {

        val dummyList = listOf(
            FindHostel(
                1, R.drawable.img_intro1, "4.8", "4-seater",
                "Sunrise Deluxe", "Rs.7,000", "Koramangala, Bangalore"
            ),
            FindHostel(
                2, R.drawable.img_intro1, "4.5", "2-seater",
                "Green Valley Hostel", "Rs.9,500", "Indiranagar, Bangalore",
                hasAc = true, hasWifi = true, hasParking = false
            ),
            FindHostel(
                3, R.drawable.img_intro1, "4.2", "Single",
                "Elite Stay", "Rs.12,000", "Whitefield, Bangalore",
                hasAc = true, hasWifi = true, hasParking = true
            ),
            FindHostel(
                4, R.drawable.img_intro1, "4.9", "3-seater",
                "Comfort Zone", "Rs.8,000", "HSR Layout, Bangalore"
            )
        )
        hostelAdapter.updateData(dummyList)
        binding.totalHostel.text = "${dummyList.size} hostel Found"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}