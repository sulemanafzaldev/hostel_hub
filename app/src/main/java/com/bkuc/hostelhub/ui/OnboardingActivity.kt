package com.bkuc.hostelhub.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.adapter.OnBoardingAdapter
import com.bkuc.hostelhub.databinding.ActivityOnboardingBinding
import com.bkuc.hostelhub.model.OnBoardingItem
import com.bkuc.hostelhub.ui.user.MainActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var dots: ArrayList<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Hide status bar (fullscreen)
     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }*/

        binding.btnSkip.setOnClickListener {
           val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Setup onboarding slides
        val onBoardingList = listOf(
            OnBoardingItem(
                R.drawable.img_intro1,
                "Find your perfect stay\n",
                "Browse hundreds of verified hostels near you with real reviews and photos."
            ),
            OnBoardingItem(
                R.drawable.img_intro2,
                "Book in seconds\n",
                "Compare rooms, prices and amenities — reserve your spot instantly.\n"
            ),
            OnBoardingItem(
                R.drawable.img_intro3,
                "Stay safe & verified\n",
                "Every hostel is verified. Chat or call owners directly before you book.\n"
            )
        )

        val adapter = OnBoardingAdapter(onBoardingList)
        binding.viewpagerOnboarding.adapter = adapter

        setupIndicator(onBoardingList.size)

        binding.viewpagerOnboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentDot(position)
            }
        })

        // Next button click
        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewpagerOnboarding.currentItem
            if (currentItem + 1 < onBoardingList.size) {
                binding.viewpagerOnboarding.currentItem = currentItem + 1
            } else {
                // TODO: Navigate to next activity when done
                goToAuthActivity()
            }
        }
    }

    private fun setupIndicator(count: Int) {
        dots = ArrayList()
        binding.sliderDots.removeAllViews()

        for (i in 0 until count) {
            val line = ImageView(this).apply {
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

    private fun setCurrentDot(position: Int) {
        for (i in dots.indices) {
            dots[i].setImageResource(R.drawable.indicator_inactive)
        }
        dots[position].setImageResource(R.drawable.dot_active)
    }

    private fun goToAuthActivity() {
        // Example: go to Login or Main screen
        startActivity(Intent(this, RoleSelectionActivity::class.java))
        finish()
    }
}
