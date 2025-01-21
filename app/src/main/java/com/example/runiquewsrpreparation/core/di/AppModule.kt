package com.example.runiquewsrpreparation.core.di

import android.content.Context
import android.content.SharedPreferences
import com.example.runiquewsrpreparation.auth.data.SessionStorageImpl
import com.example.runiquewsrpreparation.auth.domain.SessionStorage
import com.example.runiquewsrpreparation.core.data.network.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import org.intellij.lang.annotations.PrintFormat
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().build()
    }

    @Provides
    @Singleton
    fun provideSessionStorage(sharedPreferences: SharedPreferences): SessionStorage {
        return SessionStorageImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
    }
}