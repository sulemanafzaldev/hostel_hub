package com.bkuc.hostelhub.ui.admin.admindashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.adapter.admin.DashboardAdapter
import com.bkuc.hostelhub.databinding.FragmentAdminHomeBinding
import com.bkuc.hostelhub.model.DashboardItem

class AdminHomeFragment : Fragment() {

    private var _binding: FragmentAdminHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDashboard()
        setupClickListeners()
    }

    private fun setupDashboard() {
        // 1. Prepare your data list
        val dashboardItems = listOf(
            DashboardItem(1, "Total Students", "120", "↗ +12", R.drawable.ic_profile),
            DashboardItem(2, "Rooms Available", "15", "↗ +31", R.drawable.ic_bed),

            DashboardItem(5, "Complaints", "05", "↗ +11", R.drawable.ic_announcement),
        )

        // 2. Initialize the Adapter
        val adapter = DashboardAdapter(dashboardItems) { item ->
            // Handle card clicks here
            Toast.makeText(context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        // 3. Configure RecyclerView
        binding.rvDashboard.apply {
            // Since spanCount is set in XML, we just need the adapter
            this.adapter = adapter

            // Optimization: Since it's inside a NestedScrollView
            isNestedScrollingEnabled = false
        }
    }

    private fun setupClickListeners() {
        /*     binding.ivLogout.setOnClickListener {
                 // Handle logout logic
             }

             binding.ivNotification.setOnClickListener {
                 // Open notifications
             }*/
    }

    override fun onResume() {
        super.onResume()
        /*(requireActivity() as AdminActivity).apply {
            setToolbarTitle(getString(R.string.str_dashboard))
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}