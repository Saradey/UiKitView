package com.saradey.studio.uikitview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.saradey.studio.uikitview.fragments.main.list.MainListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState ?: run {
            supportFragmentManager.commit {
                replace(R.id.rcvFieldContent, MainListFragment())
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.first() is MainListFragment) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}