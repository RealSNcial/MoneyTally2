package com.yu.moneytally.forExpensesDatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yu.moneytally.databinding.ItemExpensesBinding

class ExpensesAdapter (
    var expensesList: MutableList<Expense>
    ) : RecyclerView.Adapter<ExpensesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemExpensesBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemExpensesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpensesAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
                tvAmount.text = expensesList[position].expenseRecorded
                tvDate.text = expensesList[position].dateRecorded
                tvRemarks.text = expensesList[position].remarks
            }
        }
    override fun getItemCount(): Int {
        return expensesList.size
    }
}
