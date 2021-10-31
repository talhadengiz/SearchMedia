package com.talhadengiz.hepsiburada.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talhadengiz.hepsiburada.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Hepsiburada)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}