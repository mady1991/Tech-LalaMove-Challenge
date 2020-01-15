package com.example.techchallengelalamove.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.techchallengelalamove.R
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    fun setActionBarTitle(title: String?, backEnable: Boolean) {
        toolbar!!.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(backEnable)
        setSupportActionBar(toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}