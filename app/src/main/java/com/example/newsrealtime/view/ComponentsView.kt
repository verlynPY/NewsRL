package com.example.newsrealtime.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.newsrealtime.Article
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.graphics.asImageBitmap

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
    fun ItemNews(article: Article){
        val imageModifier = Modifier.fillMaxWidth().preferredHeight(350.dp)
        var bitmap by remember { mutableStateOf<Bitmap?>(null)}
        Glide.with(AmbientContext.current).asBitmap()
            .load(article.urlToImage)
            .into(object: CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }

            })
        Box(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight())
        {
            Column(){
                Text(text = "Microsoft se declara con valor de 1 trillon de dolares", style = MaterialTheme.typography.h1)
                if(bitmap != null){
                    Image(bitmap!!.asImageBitmap(), modifier = imageModifier)
                }
            }


        }
    }



}