package com.bkuc.hostelhub.ui.user.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.adapter.FeaturedOfferAdapter
import com.bkuc.hostelhub.adapter.TopHostelsAdapter
import com.bkuc.hostelhub.databinding.FragmentHomeBinding
import com.bkuc.hostelhub.model.FeaturedOffer
import com.bkuc.hostelhub.model.TopHostel
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
   // private lateinit var adapter: AllOrderAdapter
    private lateinit var dots: ArrayList<ImageView>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        quickViewPagerItem()
        setupTopHostels()
        return binding.root
    }

    private fun setupTopHostels() {
        val topHostelsList = listOf(
            TopHostel("1", R.drawable.img_intro2, "Sunrise Deluxe", "4.8", "Abdara Road, Peshawar", "Rs.7,000", "4-seater"),
            TopHostel("2", R.drawable.img_intro1, "City Center Hostel", "4.5", "University Road, Peshawar", "Rs.8,500", "2-seater"),
            TopHostel("2", R.drawable.img_intro3, "Pakistan  Hostel", "4.5", "University Road, Peshawar", "Rs.8,500", "2-seater"),
            TopHostel("3", R.drawable.img_intro2, "Green View", "4.2", "Hayatabad, Peshawar", "Rs.6,500", "3-seater")
        )

        val topAdapter = TopHostelsAdapter(topHostelsList) { hostel ->
            // Handle click (e.g., navigate to details)
            findNavController().navigate(R.id.detailsFragment)
            Toast.makeText(requireContext(), "Clicked: ${hostel.title}", Toast.LENGTH_SHORT).show()
        }

        binding.rvTopHostels.apply {
            adapter = topAdapter
            layoutManager = LinearLayoutManager(requireContext())
            // Optional: if inside a NestedScrollView, disable nested scrolling
            isNestedScrollingEnabled = false
        }
    }


    private fun quickViewPagerItem() {
        val quickshoplist = listOf(
            FeaturedOffer(
                image = R.drawable.image_offer_card,
                title = "Student Hostel",
                subtitle = "Student Special",
                description = "50% off this month",
                offerPercent = "50%",
                badgeText = "Offer ends Mid May"
            ),
            FeaturedOffer(
                image = R.drawable.img,
                title = "Luxury Hostel",
                subtitle = "Premium Deal",
                description = "30% off this week",
                offerPercent = "30%",
                badgeText = "Limited time"
            ),
            FeaturedOffer(
                image = R.drawable.image_offer_card,
                title = "Top Hostel",
                subtitle = "Premium Deal",
                description = "10% off this week",
                offerPercent = "20%",
                badgeText = "Limited time"
            ),
            )

        val adapter = FeaturedOfferAdapter(quickshoplist)
        binding.viewpagerOffer.adapter = adapter

        setupIndicator(quickshoplist.size)
        binding.viewpagerOffer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentDot(position)
            }
        })
    }

    private fun setCurrentDot(position: Int) {
        for (i in dots.indices) {
            dots[i].setImageResource(R.drawable.indicator_inactive)
        }
        dots[position].setImageResource(R.drawable.dot_active)
    }


    private fun setupIndicator(count: Int) {
        dots = ArrayList()
        binding.sliderDots.removeAllViews()

        for (i in 0 until count) {
            val line = ImageView(requireContext()).apply {
                setImageResource(R.drawable.indicator_inactive)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(8, 0, 8, 0)
                layoutParams = params
            }
            dots.add(line)
            binding.sliderDots.addView(line)
        }

        if (dots.isNotEmpty()) {
            dots[0].setImageResource(R.drawable.dot_active)
        }
    }


}