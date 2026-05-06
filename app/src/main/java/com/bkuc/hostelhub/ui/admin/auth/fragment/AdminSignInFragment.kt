package com.bkuc.hostelhub.ui.admin.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.databinding.FragmentAdminSignInBinding

class AdminSignInFragment : Fragment() {

    private var _binding: FragmentAdminSignInBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvCreateAccount.setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
            }

        }
    }
}