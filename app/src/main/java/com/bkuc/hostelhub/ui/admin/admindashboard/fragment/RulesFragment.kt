package com.bkuc.hostelhub.ui.admin.admindashboard.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bkuc.hostelhub.adapter.admin.RulesAdapter
import com.bkuc.hostelhub.databinding.FragmentRulesBinding
import com.bkuc.hostelhub.model.RuleModel

class RulesFragment : Fragment() {

    private var _binding: FragmentRulesBinding? = null
    private val binding get() = _binding!!

    private lateinit var rulesAdapter: RulesAdapter
    private val rulesList = mutableListOf<RuleModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Handle Add Button Click
        binding.btnAddRoom.setOnClickListener {
            val ruleText = binding.etNewRule.text.toString().trim()

            if (ruleText.isNotEmpty()) {
                val newRule =
                    RuleModel(id = System.currentTimeMillis().toString(), ruleText = ruleText)
                rulesAdapter.addRule(newRule)

                // Clear input and scroll to bottom
                binding.etNewRule.text.clear()
                binding.rvRules.smoothScrollToPosition(rulesList.size - 1)
            } else {
                Toast.makeText(requireContext(), "Please enter a rule", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        rulesAdapter = RulesAdapter(rulesList) { position ->
            rulesAdapter.removeRule(position)
        }

        binding.rvRules.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rulesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}