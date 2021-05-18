package com.example.newsrealtime.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.newsrealtime.Article
import com.example.newsrealtime.R
import com.example.newsrealtime.model.Utils
import com.example.newsrealtime.model.Utils.SendDescription
import java.text.SimpleDateFormat
import java.util.*

object ComponentsView {


    @Composable
    fun stamp(){
        Column(modifier = Modifier.padding(5.dp), verticalArrangement = Arrangement.Top){
            Box(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colors.primary)
                    .padding(10.dp)){

                Text(text = "NewsRL", style = MaterialThemee.Courgette.h3,
                        color = Color.Red)

            }
        }

    }
    @Composable
    fun select(){
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Top){
            Box(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(25.dp))
                .background(MaterialTheme.colors.primary)
                .absolutePadding(left = 20.dp, right = 20.dp, top = 10.dp, bottom = 10.dp)){

                Text(text = "All", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    color = Color.Black)

            }
        }

    }

    @Composable
    fun FoodCategoryChip(
        category: String,
        onExecuteSearch: (String) -> Unit,
    ){
        Surface(
            modifier = Modifier.padding(end = 8.dp),
            elevation = 8.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.primary
        ) {
            Row(modifier = Modifier
                .clickable(
                    onClick = {
                        onExecuteSearch(category)
                    }
                )
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.body2,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }


    @Composable
    fun ItemNewsFirtsIndex(article: Article, context: Context){
        Spacer(modifier = Modifier.height(55.dp))
        val imageModifier = Modifier.fillMaxWidth().preferredHeight(280.dp)
                .clip(RoundedCornerShape(15.dp))



        var bitmap by remember { mutableStateOf<Bitmap?>(null)}
        Glide.with(AmbientContext.current).asBitmap()
            .load(article.urlToImage)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })

            Column(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp)
                    .clickable { SendDescription(context, article) }
            ){
                if(bitmap != null){
                    Image(bitmap!!.asImageBitmap(), modifier = imageModifier, contentScale = ContentScale.Crop)
                }
                else{
                    Image(imageResource(id = R.drawable.sin_imagen),
                            Modifier.fillMaxWidth().preferredHeight(280.dp)
                                    .clip(RoundedCornerShape(15.dp)),
                            contentScale = ContentScale.Crop

                    )
                }

                Text(text = "${article.title}", style = MaterialTheme.typography.h4)
            }




    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ItemNews(article: Article, context: Context){
        val imageModifier = Modifier
                .preferredWidth(150.dp)
                .preferredHeight(120.dp)
                .clip(RoundedCornerShape(10.dp))

        var bitmap by remember { mutableStateOf<Bitmap?>(null)}
        Glide.with(AmbientContext.current).asBitmap()
                .load(article.urlToImage)
                .placeholder(R.drawable.sin_imagen)
                .error(R.drawable.sin_imagen)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        bitmap = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        Box(modifier = Modifier
                .fillMaxWidth()
                .preferredHeight(150.dp)
        )
        {
            Row(modifier = Modifier
                    .padding(10.dp)
                    .clickable { SendDescription(context, article) }
            ){
                if(bitmap != null){
                    Image(bitmap!!.asImageBitmap(),
                            modifier = imageModifier,
                            contentScale = ContentScale.Crop)

                }
                else{
                    Image(imageResource(id = R.drawable.sin_imagen),
                            modifier = Modifier
                                    .preferredWidth(150.dp)
                                    .preferredHeight(120.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop

                    )
                }
                Column {
                    Text(text = "${article.title}",
                            modifier = Modifier.absolutePadding(left = 5.dp),
                            style = MaterialTheme.typography.h5
                    )



                    Spacer(modifier = Modifier.height(10.dp))

                        Text(text = "${Utils.DateFormat(article.publishedAt)}",
                                modifier = Modifier.absolutePadding(left = 5.dp),
                                style = MaterialTheme.typography.h6
                        )


                }
            }
        }
    }


    @Composable
    fun CircularProgress(){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center){
                CircularProgressIndicator(strokeWidth = 8.dp)
            }
        }

    }

    @Composable
    fun LayoutNoNetwork(){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center){
                Image(imageResource(R.drawable.no_internet),
                        modifier = Modifier
                                .preferredHeight(100.dp)
                                .preferredWidth(60.dp)

                )

                Text(text = "No Network", style = MaterialTheme.typography.body2)
            }
        }

    }

}