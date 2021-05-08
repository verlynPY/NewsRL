package com.example.newsrealtime.model.DI

import com.example.newsrealtime.viewmodel.MainViewModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
object Appmodul {

    fun provideViewModel(): MainViewModel{
        return MainViewModel()
    }

}