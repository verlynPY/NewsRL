package com.example.newsrealtime

import android.content.ContentValues.TAG
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
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsrealtime.model.Utils.SendDescription
import com.example.newsrealtime.view.ComponentsView.CircularProgress
import com.example.newsrealtime.view.ComponentsView.ItemNews
import com.example.newsrealtime.view.ComponentsView.ItemNewsFirtsIndex
import com.example.newsrealtime.view.ComponentsView.LayoutNoNetwork
import com.example.newsrealtime.view.ComponentsView.select
import com.example.newsrealtime.view.ComponentsView.stamp
import com.example.newsrealtime.view.MaterialThemee
import com.example.newsrealtime.view.MaterialThemee.QuizSans
import com.example.newsrealtime.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragments.newInstance] factory method to
 * create an instance of this fragment.
 */
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
                        //Text(text = "Funcionando", style = MaterialTheme.typography.h2)
                        //Column {
                        Row() {
                            stamp()
                            select()
                        }
                            ShowItems()
                        //}


                        /*LazyColumn {
                            itemsIndexed(items = ListArticle){ index, ListArticle ->
                                ItemNews(ListArticle)
                            }
                        }*/
                        //GlobalScope.launch(Dispatchers.Main){


                        //}
                        //Text(text = "Hola", style = MaterialTheme.typography.h2)
                    }
                }
            }
        }
    }

    @Composable
    fun ShowItems(){

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

        if(!Active.value){
            var TIME = remember { mutableStateOf(false) }
            CircularProgress()
            /*val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    TIME.value = true

                }
            }, TIEMPO)

            if(TIME.value){

            }*/
        }

        else if(Active.value){
            LazyColumn {
                itemsIndexed(items = ListArticle){ index, Article ->
                    when (index){
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