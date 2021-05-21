package com.example.newsrealtime.view

import android.content.Context
import android.os.Handler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsrealtime.Article
import com.example.newsrealtime.view.ComponentsView.CircularProgress
import com.example.newsrealtime.view.ComponentsView.ItemNews
import com.example.newsrealtime.view.ComponentsView.ItemNewsFirtsIndex

object UtilsView {


    val TIEMPO = 5000L

    @Composable
    fun LoadingData(onClick: () -> Unit){
        var TIME = remember { mutableStateOf(false) }
        CircularProgress()
        val handler = Handler()
        handler.postDelayed({ TIME.value = true }, TIEMPO)
        if(TIME.value){
            ComponentsView.NotNetwork(onClick = onClick)
        }
    }

    @Composable
    fun LoadedData(context: Context, ListArticle: ArrayList<Article>, ShowFirtsItem: Boolean){
        Column {
            Spacer(modifier = Modifier.height(70.dp))
            LazyColumn {
                itemsIndexed(items = ListArticle) { index, Article ->
                    if(
                        !Article.description.isNullOrEmpty() ||
                        !Article.content.isNullOrEmpty() ||
                        !Article.author.isNullOrEmpty()) {
                            if(ShowFirtsItem){
                                when (index) {
                                    0 -> {

                                        context?.let { ItemNewsFirtsIndex(Article, it) }

                                    }
                                    else -> {

                                        context?.let { ItemNews(Article, it) }

                                    }
                                }
                            }
                        else{
                                context?.let { ItemNews(Article, it) }
                            }

                    }
                }
            }
        }
    }

}