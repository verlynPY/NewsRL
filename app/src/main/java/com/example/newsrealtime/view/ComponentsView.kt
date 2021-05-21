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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.newsrealtime.Article
import com.example.newsrealtime.R
import com.example.newsrealtime.model.Repository.Room.History
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

                Text(text = stringResource(R.string.newsrl), style = MaterialThemee.Courgette.h3,
                        color = Color.Red)

            }
        }

    }

    @Composable
    fun All(){
        Column(modifier = Modifier.absolutePadding(left = 8.dp, top = 10.dp),
            verticalArrangement = Arrangement.Top)

        {
            Box(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.primary)
                .absolutePadding(left = 20.dp, right = 20.dp, top = 10.dp, bottom = 10.dp)){

                Text(text = "All", fontSize = 16.sp, fontWeight = FontWeight.Bold,
                    color = Color.Gray)

            }
        }

    }



    @Composable
    fun NotNetwork(onClick : () -> Unit ){
        Box(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
            stamp()
            Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
            {
                Image(imageResource(R.drawable.no_internet), modifier = Modifier.height(100.dp).width(100.dp))
                Text(text = "No Connection Network", style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(30.dp))
                IconButton(onClick = onClick)
                {

                    Icon(vectorResource(R.drawable.ic_refresh), modifier = Modifier.size(36.dp))
                }

            }
        }
    }

    @Composable
    fun ShowHistory(list: ArrayList<History>){
        LazyColumn {
            itemsIndexed(items = list){ index, item ->
                Text(text = "${item.Search}")
            }
        }
    }

    val imageModifier = Modifier.fillMaxWidth().preferredHeight(280.dp)
        .clip(RoundedCornerShape(15.dp))

    @Composable
    fun ItemNewsFirtsIndex(article: Article, context: Context){
        //Spacer(modifier = Modifier.height(55.dp))

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
                    .clickable {
                        SendDescription(context, article)
                    }
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
                Surface(contentColor = MaterialTheme.colors.primary, color = Color.Transparent) {
                    Text(text = "${article.title}", style = MaterialTheme.typography.h4)
                }
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
        Glide.with(AmbientContext.current)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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
                    var Article: String = ""
                    if(article.title!!.length > 100){
                        Article = article.title!!.substring(0,100) + "...."
                    }
                    else{
                        Article = article.title.toString()
                    }
                    Surface(contentColor = MaterialTheme.colors.primary, color = Color.Transparent) {
                        Text(
                            text = Article,
                            modifier = Modifier.absolutePadding(left = 5.dp),
                            style = MaterialTheme.typography.h5
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                        Text(text = "${Utils.DateFormat(article.publishedAt)}",
                                modifier = Modifier.absolutePadding(left = 5.dp),
                                style = MaterialTheme.typography.h6
                        )


                }
            }
        }
    }


    @ExperimentalStdlibApi
    @Composable
    fun DropDownList(
        requestToOpen: Boolean = false,
        list: List<String>,
        request: (Boolean) -> Unit,
        selectedString: (String) -> Unit
    ){
        DropdownMenu(
            dropdownModifier = Modifier.preferredWidth(150.dp),
            toggle = {

            },
            expanded = requestToOpen,
            onDismissRequest = { request(false) }
        ){
            list.forEach {
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(),onClick = {
                    request(false)
                    selectedString(it)
                }) {
                    Text(it.uppercase(), modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start))
                }
            }
        }
    }


    @Composable
    fun NotResultYet(){
        val cardModifier = Modifier.fillMaxWidth().preferredHeight(250.dp).absolutePadding(top = 15.dp)
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(90.dp))
            Card(
                modifier = cardModifier,
                shape = RoundedCornerShape(25.dp),
                backgroundColor = Color(230,230,230)

            ) {
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = stringResource(R.string.newsrl), style = MaterialThemee.Courgette.h2,
                        color = Color.Red
                    )
                }

            }
            Card(
                modifier = cardModifier,
                shape = RoundedCornerShape(25.dp),
                backgroundColor = Color(230,230,230)

            ) {
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = stringResource(R.string.newsrl), style = MaterialThemee.Courgette.h2,
                        color = Color.Red
                    )
                }

            }
            Card(
                modifier = cardModifier,
                shape = RoundedCornerShape(25.dp),
                backgroundColor = Color(230,230,230)

            ) {
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = stringResource(R.string.newsrl), style = MaterialThemee.Courgette.h2,
                        color = Color.Red
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



}