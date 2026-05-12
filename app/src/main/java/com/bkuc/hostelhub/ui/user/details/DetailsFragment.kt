package com.bkuc.hostelhub.ui.user.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.adapter.HostelImageAdapter
import com.bkuc.hostelhub.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val imageList = listOf(
            R.drawable.img_intro1,
            R.drawable.img_intro2,
            R.drawable.img_intro3,
            R.drawable.img_intro1,
            R.drawable.img_intro3
        )

        val adapter = HostelImageAdapter(imageList)

        binding.recyclerViewImages.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerViewImages.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}