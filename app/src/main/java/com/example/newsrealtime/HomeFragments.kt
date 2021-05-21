package com.example.newsrealtime

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsrealtime.model.Utils
import com.example.newsrealtime.view.ComponentsView
import com.example.newsrealtime.view.ComponentsView.All
import com.example.newsrealtime.view.ComponentsView.CircularProgress
import com.example.newsrealtime.view.ComponentsView.ItemNews
import com.example.newsrealtime.view.ComponentsView.ItemNewsFirtsIndex
import com.example.newsrealtime.view.ComponentsView.NotNetwork
import com.example.newsrealtime.view.ComponentsView.stamp
import com.example.newsrealtime.view.MaterialThemee
import com.example.newsrealtime.view.MaterialThemee.QuizSans
import com.example.newsrealtime.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@Suppress("DEPRECATION")
class HomeFragments : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val TIEMPO = 5000L

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @ExperimentalStdlibApi
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply{
            setContent{
                MaterialTheme(
                        typography = QuizSans,
                        colors = if (isSystemInDarkTheme()) MaterialThemee.DarkColor
                        else MaterialThemee.LigthColor)
                {
                    ConstraintLayout(Modifier.background(MaterialTheme.colors.background)){
                        val text = remember { mutableStateOf("US") }
                        val isOpen = remember { mutableStateOf(false) }

                        val openCloseOfDropDownList: (Boolean) -> Unit = {
                            isOpen.value = it
                        }
                        val userSelectedString: (String) -> Unit = {
                            text.value = it
                        }

                        Row() {
                            stamp()
                            ComponentsView.DropDownList(
                                requestToOpen = isOpen.value,
                                list = Utils.ListCountry,
                                openCloseOfDropDownList,
                                userSelectedString
                            )
                            
                            All()
                            Column(modifier = Modifier.fillMaxWidth().absolutePadding(top = 10.dp, right = 10.dp), horizontalAlignment = Alignment.End){
                            Button( modifier = Modifier.preferredWidth(80.dp).clip(
                                RoundedCornerShape(20.dp)).preferredHeight(40.dp).background(MaterialTheme.colors.primary), onClick = {
                                isOpen.value = true
                            }){
                                Text(text = text.value.uppercase(), fontSize = 16.sp, fontWeight = FontWeight.Bold,
                                    color = Color.Gray)
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                                    
                                         Icon(Icons.Default.ExpandMore, tint = Color.Red)
                                }

                            }


                        }
                        }
                            ShowItems(text.value)
                    }
                }
            }
        }
    }

    @Composable
    fun ShowItems(Country: String) {
        viewModel.EmitTopNews(Country)
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected) {
            var Active = remember { mutableStateOf(false) }
            var ListArticle = ArrayList<Article>()
            lifecycleScope.launchWhenStarted {
                // Triggers the flow and starts listening for values
                viewModel.uiState.collect { uiState ->
                    // New value received
                    when (uiState) {
                        is MainViewModel.LatestNewsUiState.Success -> {
                            //Log.e(TAG,"${uiState.news}")
                            if (uiState.news != null) {
                                for (i in uiState.news) {
                                    ListArticle.add(i)
                                    if (!ListArticle.isEmpty()) {
                                        Active.value = true
                                    }
                                }
                                //ItemNews(uiState.news.get(4))
                                Log.e(TAG, "${uiState.news}")
                            }
                        }
                        is MainViewModel.LatestNewsUiState.Error -> Log.e(TAG, "${uiState.exception}")
                    }
                }
            }

            if (!Active.value) {
                var TIME = remember { mutableStateOf(false) }
                CircularProgress()
                val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {

                    TIME.value = true
                }
            }, TIEMPO)
            if(TIME.value){

                NotNetwork(onClick = {
                    Refresh()
                })
            }
            }
            else if (Active.value) {
                Column {
                    Spacer(modifier = Modifier.height(70.dp))
                    LazyColumn {
                        itemsIndexed(items = ListArticle) { index, Article ->
                            if(!Article.description.isNullOrEmpty() || !Article.content.isNullOrEmpty() || !Article.author.isNullOrEmpty()) {
                                when (index) {
                                    0 -> {

                                        context?.let { ItemNewsFirtsIndex(Article, it) }


                                    }
                                    else -> {

                                        context?.let { ItemNews(Article, it) }

                                    }
                                }
                            }
                        }
                    }

                }
            }

        }
        else{
            NotNetwork(onClick = {
                when (isConnected){
                    true -> {
                        Refresh()
                    }
                    false -> {
                        Toast.makeText(context, "Not Connected", Toast.LENGTH_SHORT).show()
                        Refresh()
                    }
                }
            })
        }
    }



    @SuppressLint("UseRequireInsteadOfGet")
    fun Refresh(){
        val ft = fragmentManager!!.beginTransaction()
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false)
        }
        ft.detach(this)
            .attach(this)
            .commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragments.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragments().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}