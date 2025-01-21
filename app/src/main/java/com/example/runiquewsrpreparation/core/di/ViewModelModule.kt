package com.example.runiquewsrpreparation.core.di

import com.example.runiquewsrpreparation.auth.data.AuthRepositoryImpl
import com.example.runiquewsrpreparation.auth.data.EmailPatternValidator
import com.example.runiquewsrpreparation.auth.domain.AuthRepository
import com.example.runiquewsrpreparation.auth.domain.PatternValidator
import com.example.runiquewsrpreparation.auth.domain.SessionStorage
import com.example.runiquewsrpreparation.auth.domain.UserDataValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.HttpClient
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    @Named("EmailValidator")
    fun providePatternValidator(): PatternValidator {
        return EmailPatternValidator
    }

    @Provides
    @ViewModelScoped
    fun provideUserDataValidator(
        @Named("EmailValidator") patternValidator: PatternValidator
    ): UserDataValidator {
        return UserDataValidator(patternValidator)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthRepository(
        httpClient: HttpClient,
        sessionStorage: SessionStorage
    ): AuthRepository {
        return AuthRepositoryImpl(httpClient, sessionStorage)
    }
}