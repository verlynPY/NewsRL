package com.example.newsrealtime.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object ComponentsView {


    @Composable
    fun stamp(){
        Column(modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.Top){
            Box(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colors.primary)
                    .padding(10.dp)){

                Text(text = "NewsRL", style = MaterialThemee.Courgette.h3,
                        color = MaterialTheme.colors.secondary)

            }
        }

    }

    @Composable
    fun ItemNews(){
        Box(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight())
        {
            Column(){
                Text(text = "Microsoft se declara con valor de 1 trillon de dolares", style = MaterialTheme.typography.h1)

            }


        }
    }

}