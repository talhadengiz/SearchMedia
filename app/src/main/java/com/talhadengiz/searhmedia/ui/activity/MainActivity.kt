package com.talhadengiz.searhmedia.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talhadengiz.searhmedia.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Hepsiburada)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}