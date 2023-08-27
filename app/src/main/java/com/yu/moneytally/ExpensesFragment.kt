package com.yu.moneytally

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yu.moneytally.databinding.FragmentExpensesBinding
import com.yu.moneytally.forExpensesDatabase.Expense
import com.yu.moneytally.forExpensesDatabase.ExpensesAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExpensesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ExpensesFragment() : Fragment() {

    private var expensesBinding: FragmentExpensesBinding? = null
    private val binding get() = expensesBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun sampleExpensesList(): MutableList<Expense>{
        return mutableListOf(
            Expense("Amount", "Date", "Remarks"),
            Expense("1000", "26/08/2023", "sample1"),
            Expense("4000", "26/08/2023", "sample2"),
            Expense("2000", "26/08/2023", "sample3")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        expensesBinding = FragmentExpensesBinding.inflate(inflater, container, false)
        return expensesBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val expensesAdapter = ExpensesAdapter(sampleExpensesList())
        binding.rvExpenses.apply {
            adapter = expensesAdapter
            layoutManager = LinearLayoutManager(this@ExpensesFragment.context)
            itemAnimator = null
        }
        setupButtonAdd(expensesAdapter)
        setupSwipeLeftToDelete(expensesAdapter)
    }

    private fun setupButtonAdd(expensesAdapter: ExpensesAdapter) {
        binding.expensesSubmitButton.setOnClickListener {
            val expensesAmount = binding.expensesAmountText.text.toString()
            val dateRecorded = binding.expensesDateText.text.toString()
            val remarks = binding.expensesRemarksText.text.toString()
            if (expensesAmount.isNotEmpty()) {
                expensesAdapter.expensesList.add(Expense(expensesAmount, dateRecorded, remarks ))
                expensesAdapter.notifyItemInserted(expensesAdapter.expensesList.size - 1)
                binding.expensesAmountText.text?.clear()
                binding.expensesDateText.text?.clear()
                binding.expensesRemarksText.text?.clear()
            }
        }
    }
    private fun setupSwipeLeftToDelete(expensesAdapter: ExpensesAdapter) {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val removedItem = expensesAdapter.expensesList.removeAt(position)
                expensesAdapter.notifyItemRemoved(position)

                Snackbar.make(
                    binding.root, getString(R.string.item_removed), Snackbar.LENGTH_LONG
                )
                    .setAction(getString(R.string.undo)) {
                        // Add the item back to the original data source
                        expensesAdapter.expensesList.add(position, removedItem)
                        expensesAdapter.notifyItemInserted(position)
                    }
                    .show()
            }
        }).attachToRecyclerView(binding.rvExpenses)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment expensesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExpensesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}