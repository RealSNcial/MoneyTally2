package com.yu.moneytally

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yu.moneytally.forExpensesDatabase.*
import com.yu.moneytally.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val fragmentManager : FragmentManager = supportFragmentManager

        //define the fragments
        val fragment1: Fragment = SavingsFragment()
        val fragment2 : Fragment = ExpensesFragment()
        val fragment3 : Fragment = AlarmsFragment()

        navView.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.navigation_savings -> fragment1
                R.id.navigation_expenses -> fragment2
                R.id.navigation_alarms -> fragment3
                else -> TODO()
            }
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()
            true
        }
        navView.selectedItemId = R.id.navigation_savings
    }
}