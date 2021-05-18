package com.example.newsrealtime.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.newsrealtime.R
import com.example.newsrealtime.model.Utils.AUTHOR
import com.example.newsrealtime.model.Utils.CONTENT
import com.example.newsrealtime.model.Utils.DESCRIPTION
import com.example.newsrealtime.model.Utils.PUBLISHEDAT
import com.example.newsrealtime.model.Utils.TITLE
import com.example.newsrealtime.model.Utils.URLIMAGE
import com.example.newsrealtime.view.ui.theme.NewsRealTimeTheme

class DescriptionNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        var Title = bundle!!.getCharSequence(TITLE)
        var Author = bundle!!.getCharSequence(AUTHOR)
        var Content = bundle!!.getCharSequence(CONTENT)
        var Description = bundle!!.getCharSequence(DESCRIPTION)
        var PublishedAt = bundle!!.getCharSequence(PUBLISHEDAT)
        var UrlImage = bundle!!.getCharSequence(URLIMAGE)

        setContent {
            MaterialTheme(
                typography = MaterialThemee.QuizSans,
                colors = if (isSystemInDarkTheme()) MaterialThemee.DarkColor
                else MaterialThemee.LigthColor
            )
            {
                ConstraintLayout(Modifier.background(MaterialTheme.colors.background)) {
                    ScrollableColumn {

                        //Column(modifier = Modifier.fillMaxSize()) {
                        var bitmap by remember { mutableStateOf<Bitmap?>(null) }

                        Glide.with(AmbientContext.current).asBitmap()
                            .load(UrlImage)
                                .placeholder(R.drawable.sin_imagen)
                                .error(R.drawable.sin_imagen)
                            .into(object : CustomTarget<Bitmap>() {
                                override fun onResourceReady(
                                    resource: Bitmap,
                                    transition: Transition<in Bitmap>?
                                ) {
                                    bitmap = resource
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {

                                }

                            })

                        Box(modifier = Modifier.padding(5.dp)) {
                            if(bitmap != null) {
                                Image(
                                        bitmap!!.asImageBitmap(),
                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .preferredHeight(400.dp)
                                                .clip(
                                                        RoundedCornerShape(
                                                                bottomLeft = 30.dp,
                                                                bottomRight = 30.dp
                                                        )
                                                ),
                                        contentScale = ContentScale.Crop
                                )
                            }
                            else{
                                Image(imageResource(id = R.drawable.sin_imagen),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .preferredHeight(400.dp)
                                        .clip(
                                            RoundedCornerShape(
                                                bottomLeft = 30.dp,
                                                bottomRight = 30.dp
                                            )
                                        ),
                                    contentScale = ContentScale.Crop

                                )
                            }

                            IconButton(onClick = {
                                finish()
                            }){

                                    Icon(Icons.Filled.ArrowBackIos)

                        }
                        }

                        Text(
                            text = "$Title",
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.h4
                        )

                        Text(
                            text = "$Author",
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.h5
                        )

                        Text(
                            text = "$PublishedAt",
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.h6
                        )

                        Text(
                            text = "$Content",
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.h6
                        )

                        Text(
                            text = "$Description",
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.h6
                        )


                    }
                }
            }
        }
        //}
    }
}

