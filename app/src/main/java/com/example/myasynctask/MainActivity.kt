package com.example.myasynctask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button
        button = findViewById(R.id.button)
        button.setOnClickListener {
            MyAsyncTask(this).execute("Param1");
        }
    }
}