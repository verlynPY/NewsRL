package com.example.newsrealtime

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsrealtime.ui.theme.NewsRealTimeTheme
import com.example.newsrealtime.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "newsrealtime"

    private lateinit var viewmodel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        /*viewmodel.GetNews("Apple").observe(this, Observer {
            Log.e(TAG, "${it.articles}")
        })*/
        setContent {
            NewsRealTimeTheme {



            }
        }
    }
}

