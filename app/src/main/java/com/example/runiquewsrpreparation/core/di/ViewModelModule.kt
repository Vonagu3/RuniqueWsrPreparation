package com.example.runiquewsrpreparation.core.di

import com.example.runiquewsrpreparation.auth.data.EmailPatternValidator
import com.example.runiquewsrpreparation.auth.domain.PatternValidator
import com.example.runiquewsrpreparation.auth.domain.UserDataValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
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
}