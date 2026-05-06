package com.bkuc.hostelhub.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bkuc.hostelhub.databinding.ActivityRoleSelectionBinding
import com.bkuc.hostelhub.ui.admin.auth.AdminAuthActivity
import com.bkuc.hostelhub.ui.user.MainActivity

class RoleSelectionActivity : AppCompatActivity() {

    private var _binding: ActivityRoleSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityRoleSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // User clicks hostel card -> MainActivity
        binding.cardUser.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // Owner clicks admin card -> AdminAuthActivity
        binding.cardAdmin.setOnClickListener {
            startActivity(Intent(this, AdminAuthActivity::class.java))
            finish()
        }

        // Back button
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}