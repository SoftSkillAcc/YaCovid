package com.example.yacovid.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yacovid.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            val transaction = supportFragmentManager.beginTransaction()
            val countryListFragment = CountryListFragment()
            transaction.replace(R.id.container, countryListFragment)
            transaction.commit()
        }
    }
}