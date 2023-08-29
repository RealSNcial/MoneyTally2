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
import com.yu.moneytally.databinding.FragmentAlarmsBinding
import com.yu.moneytally.databinding.FragmentExpensesBinding
import com.yu.moneytally.forExpensesDatabase.Alarm
import com.yu.moneytally.forExpensesDatabase.AlarmAdapter
import com.yu.moneytally.forExpensesDatabase.Expense
import com.yu.moneytally.forExpensesDatabase.ExpensesAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlarmsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlarmsFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var alarmBinding: FragmentAlarmsBinding? = null
    private val binding get() = alarmBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private fun sampleAlarmsList(): MutableList<Alarm>{
        return mutableListOf(
            Alarm("Amount", "Date", "Remarks"),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        alarmBinding = FragmentAlarmsBinding.inflate(inflater, container, false)
        return alarmBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        val alarmAdapter = AlarmAdapter(sampleAlarmsList())
        binding.rvAlarms.apply {
            adapter = alarmAdapter
            layoutManager = LinearLayoutManager(this@AlarmsFragment.context)
            itemAnimator = null
        }
        setupButtonAdd(alarmAdapter)
        setupSwipeLeftToDelete(alarmAdapter)
    }

    private fun setupButtonAdd(alarmAdapter: AlarmAdapter) {
        binding.alarmsSubmitButton.setOnClickListener {
            val alarmAmount = binding.alarmAmount.text.toString()
            val dateRecorded = binding.alarmsDateWhenToPay.text.toString()
            val remarks = binding.alarmRemarksText.text.toString()
            if (alarmAmount.isNotEmpty()) {
                alarmAdapter.alarmList.add(Alarm(alarmAmount, dateRecorded, remarks ))
                alarmAdapter.notifyItemInserted(alarmAdapter.alarmList.size - 1)
                binding.alarmAmount.text?.clear()
                binding.alarmsDateWhenToPay.text?.clear()
                binding.alarmRemarksText.text?.clear()
            }
        }
    }

    private fun setupSwipeLeftToDelete(alarmAdapter: AlarmAdapter) {
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
                val removedItem = alarmAdapter.alarmList.removeAt(position)
                alarmAdapter.notifyItemRemoved(position)

                Snackbar.make(
                    binding.root, getString(R.string.item_removed), Snackbar.LENGTH_LONG
                )
                    .setAction(getString(R.string.undo)) {
                        // Add the item back to the original data source
                        alarmAdapter.alarmList.add(position, removedItem)
                        alarmAdapter.notifyItemInserted(position)
                    }
                    .show()
            }
        }).attachToRecyclerView(binding.rvAlarms)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment arlarmsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlarmsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}