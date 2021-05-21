package com.example.newsrealtime.view

import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.example.newsrealtime.R


object MaterialThemee {

    private val Lobster_Two = fontFamily(
            font(R.font.newsreadervariable
            )
    )

    private val CourgetteRegural = fontFamily(
            font(R.font.ranchoregular
            )
    )

    val DarkColor = darkColors(
        primary = Color(240,240,240),
        secondary = Color(255,255,255),
        background = Color(0,0,0),
        onBackground = Color(0,0,0)
    )

    val LigthColor = lightColors(
        primary = Color(0,0,0),
        secondary = Color(255,255,255),
        background = Color(255,255,255)
    )

    val QuizSans = Typography(
        h2 = TextStyle(
            fontFamily = Lobster_Two,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp
        ),
        h3 = TextStyle(
            fontFamily = Lobster_Two,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp
        ),
            h4 = TextStyle(
                    fontFamily = Lobster_Two,
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp
            ),
            h5 = TextStyle(
                    fontFamily = Lobster_Two,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
            ),
            h6 = TextStyle(
                    fontFamily = Lobster_Two,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
            )


    )



    val Courgette = Typography(
        h1 = TextStyle(
            fontFamily = CourgetteRegural,
            fontWeight = FontWeight.Bold,
            fontSize = 80.sp
        ),
        h2 = TextStyle(
            fontFamily = CourgetteRegural,
            fontWeight = FontWeight.Bold,
            fontSize = 72.sp
        ),
            h3 = TextStyle(
                    fontFamily = CourgetteRegural,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
            )
    )


}