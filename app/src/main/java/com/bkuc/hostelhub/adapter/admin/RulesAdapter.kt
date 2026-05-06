package com.bkuc.hostelhub.adapter.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkuc.hostelhub.databinding.ItemRuleCardBinding // Ensure you use ViewBinding
import com.bkuc.hostelhub.model.RuleModel

class RulesAdapter(
    private val rules: MutableList<RuleModel>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<RulesAdapter.RuleViewHolder>() {

    inner class RuleViewHolder(val binding: ItemRuleCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuleViewHolder {
        val binding = ItemRuleCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RuleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RuleViewHolder, position: Int) {
        val rule = rules[position]
        with(holder.binding) {
            // Set the number (position + 1)
            tvRuleNumber.text = (position + 1).toString()
            // Set the rule text
            tvAdTitle.text = rule.ruleText

            // Delete action
            btnDeleteAd.setOnClickListener {
                onDeleteClick(position)
            }
        }
    }

    override fun getItemCount(): Int = rules.size

    fun addRule(rule: RuleModel) {
        rules.add(rule)
        notifyItemInserted(rules.size - 1)
    }

    fun removeRule(position: Int) {
        rules.removeAt(position)
        notifyItemRemoved(position)
        // Refresh indices for the numbers (1, 2, 3...)
        notifyItemRangeChanged(position, rules.size)
    }
}