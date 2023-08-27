package com.yu.moneytally.forExpensesDatabase
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yu.moneytally.databinding.ItemAlarmsBinding

class AlarmAdapter(
    var alarmList: MutableList<Alarm>
    ) : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemAlarmsBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAlarmsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            tvAmount.text = alarmList[position].amountToPay
            tvDate.text = alarmList[position].dateWhenToPay
            tvRemarks.text = alarmList[position].alarmRemarks
        }
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }
}
