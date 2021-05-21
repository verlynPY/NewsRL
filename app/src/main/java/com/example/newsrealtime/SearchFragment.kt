package com.example.newsrealtime

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsrealtime.model.ConnectionManager
import com.example.newsrealtime.view.ComponentsView
import com.example.newsrealtime.view.ComponentsView.ItemNews
import com.example.newsrealtime.view.ComponentsView.NotResultYet
import com.example.newsrealtime.view.MaterialThemee
import com.example.newsrealtime.view.UtilsView
import com.example.newsrealtime.viewmodel.HistoryViewModel
import com.example.newsrealtime.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SearchFragment : Fragment() {

    private val viewModel: HistoryViewModel by viewModels()
    private val mainviewModel: MainViewModel by viewModels()


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
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {

            setContent {

                MaterialTheme(
                    typography = MaterialThemee.QuizSans,
                    colors = if (isSystemInDarkTheme()) MaterialThemee.DarkColor
                    else MaterialThemee.LigthColor)
                {
                    ConstraintLayout(Modifier.background(MaterialTheme.colors.background)) {

                        //viewModel.SettingDataBase(context)
                        viewModel.GetHistory(context)
                        /*lifecycleScope.launchWhenStarted {
                            viewModel.uiState.collect { uiState ->
                                when (uiState){
                                    is HistoryViewModel.HistoryNewsUiState.Success -> {
                                        Toast.makeText(context, "${uiState.history}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }*/
                            ComponentsView.stamp()
                            var Search = remember { mutableStateOf("") }
                        Row(modifier = Modifier.fillMaxWidth().absolutePadding(left = 100.dp, top = 10.dp)) {
                            TextField(
                                    value = Search.value,
                                    onValueChange = { Search.value = it },
                                    modifier = Modifier
                                            .background(
                                                    brush = SolidColor(Color.Gray),
                                                            shape = RoundedCornerShape(30.dp)
                                            ),
                                    placeholder = { Text(text = "Search", style = MaterialTheme.typography.h5) },
                                    leadingIcon = { Icon(vectorResource(R.drawable.ic_search), modifier = Modifier.size(24.dp), tint = Color.Gray) },
                                    activeColor = Color.Transparent,
                                    backgroundColor = Color.Transparent,
                                    inactiveColor = Color.Transparent,
                                    textStyle = MaterialTheme.typography.h5

                            )
                        }
                        if(!Search.value.equals("")){
                            mainviewModel.EmitDataSearch(Search.value)
                            ShowItem(context)
                        }
                        else{
                            NotResultYet()
                        }
                    }
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    @Composable
    fun ShowItem(context: Context){
        var isConnected = ConnectionManager().VerifyConnection(context)
        if (isConnected) {
            var Active = remember { mutableStateOf(false) }
            var ListArticle = ArrayList<Article>()
            lifecycleScope.launchWhenStarted {
                // Triggers the flow and starts listening for values
                mainviewModel.uiState.collect { uiState ->
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
                                Log.e(ContentValues.TAG, "${uiState.news}")
                            }
                        }
                        is MainViewModel.LatestNewsUiState.Error -> Log.e(ContentValues.TAG, "${uiState.exception}")
                    }
                }
            }

            if (!Active.value) {
                UtilsView.LoadingData(onClick = { Refresh() })
            }
            else if (Active.value) {
                UtilsView.LoadedData(context, ListArticle, false)
            }

        }
        else{
            ComponentsView.NotNetwork(onClick = {
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
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}