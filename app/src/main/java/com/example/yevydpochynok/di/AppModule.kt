package com.example.yevydpochynok.di

import android.app.Application
import com.example.yevydpochynok.data.manager.LocalUserManagerImpl
import com.example.yevydpochynok.domain.manager.LocalUserManager
import com.example.yevydpochynok.domain.usecases.GetCategories
import com.example.yevydpochynok.domain.usecases.HomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideHomeUseCases() : HomeUseCases {
        return HomeUseCases(
            getCategories = GetCategories()
        )
    }
}