package com.android.example.skbeonpropinvest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentWelcome = WelcomeFragment()

        transaction.replace(R.id.fragment, fragmentWelcome)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
