package com.yu.moneytally.forExpensesDatabase
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Expense(
    val expenseRecorded: String,
    val dateRecorded: String,
    val remarks: String,

)
