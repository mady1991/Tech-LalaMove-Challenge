package com.example.techchallengelalamove.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.techchallengelalamove.R
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setHomeAsEnable(false)
    }

    private fun setHomeAsEnable(enable: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enable)
        setSupportActionBar(toolbar)
    }

    fun setActionBarTitle(title: String?, backEnable: Boolean) {
        toolbar!!.title = title
        setHomeAsEnable(backEnable)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}