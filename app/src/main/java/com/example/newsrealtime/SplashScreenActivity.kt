package com.example.newsrealtime

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import com.example.newsrealtime.view.MainActivity
import com.example.newsrealtime.view.MaterialThemee
import com.example.newsrealtime.view.MaterialThemee.QuizSans

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContent{
                MaterialTheme(
                    typography = QuizSans,
                    colors = if (isSystemInDarkTheme()) MaterialThemee.DarkColor
                    else MaterialThemee.LigthColor)
                {
                    ConstraintLayout(Modifier.background(MaterialTheme.colors.background)) {
                        Column(modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "NewsRL", style = MaterialThemee.Courgette.h1,
                                color = Color.Red
                            )
                        }

                        val handler = Handler()
                        handler.postDelayed(object: Runnable{
                            override fun run() {
                                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                                startActivity(intent)
                                overridePendingTransition(0,0)
                                finish()
                            }

                        }, 2000)

                    }
                    }

                }

    }
}

