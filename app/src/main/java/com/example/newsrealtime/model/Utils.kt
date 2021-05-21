package com.example.newsrealtime.model

import android.content.Context
import android.content.Intent
import android.icu.util.ULocale.getCountry
import android.os.Bundle
import com.example.newsrealtime.Article
import com.example.newsrealtime.view.DescriptionNewsActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {


    val TITLE = "title"
    val AUTHOR = "author"
    val CONTENT = "content"
    val DESCRIPTION = "description"
    val PUBLISHEDAT = "publishedAt"
    val URLIMAGE = "urlImage"

    var ListCountry = listOf("ae","ar","at","au","be","bg","br","ca","ch","cn","co","cu",
        "cz","de","eg","fr","gb", "gr","hk","hu","id","ie","il","in","it","jp","kr","lt",
        "lv","ma","mx","my","ng", "nl","no","nz","ph","pl","pt","ro","rs","ru","sa","se",
        "sg","si","sk","th","tr","tw","ua","us","ve","za")

    fun SendDescription(context: Context, article: Article){

        val intent = Intent(context, DescriptionNewsActivity::class.java)
        val bundle = Bundle()
        bundle.putString(TITLE, article.title)
        bundle.putString(AUTHOR, article.author)
        bundle.putString(CONTENT, article.content)
        bundle.putString(DESCRIPTION, article.description)
        bundle.putString(PUBLISHEDAT, article.publishedAt)
        bundle.putString(URLIMAGE, article.urlToImage)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtras(bundle)
        context.startActivity(intent)


    }




    fun DateFormat(oldstringDate: String?): String? {
        val newDate: String?
        val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale.getDefault())
        newDate = try {
            val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldstringDate
        }
        return newDate
    }

}