package com.bkuc.hostelhub.ui.user.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.SavedItemAdapter
import com.bkuc.hostelhub.databinding.FragmentSavedBinding
import com.bkuc.hostelhub.model.SaveItemModel
import retrofit2.http.GET

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dummy Data
        val propertyList = listOf(
            SaveItemModel(
                "1",
                "https://i1-e.pinimg.com/736x/31/b4/5c/31b45c69909021dbdb77124bfb71e536.jpg",
                "Sunrise Deluxe",
                "Koramangala, Bangalore",
                "₹7,800/mo",
                "4.8"
            ),
            SaveItemModel(
                "1",
                "https://i1-e.pinimg.com/736x/b0/4b/1a/b04b1ab4f07a76aa7f221dece2637b27.jpg",
                "Sunrise Deluxe",
                "Koramangala, Bangalore",
                "₹7,800/mo",
                "4.8"
            ),
            SaveItemModel(
                "1",
                "https://i1-e.pinimg.com/736x/67/30/f4/6730f4a3b43af646c00542f0542ff8ff.jpg",
                "Sunrise Deluxe",
                "Koramangala, Bangalore",
                "₹7,800/mo",
                "4.8"
            )
        )

// Adapter Initialize karein
        val savedItemAdapter = SavedItemAdapter(propertyList) { clickedItem ->
            // Heart click par jo action perform karna ho (e.g., item remove karna ya API call)
            Toast.makeText(requireContext(), "${clickedItem.title} clicked", Toast.LENGTH_SHORT)
                .show()
        }

// RecyclerView ko adapter assign karein
        binding.rvSavedItem.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = savedItemAdapter
        }
    }
}