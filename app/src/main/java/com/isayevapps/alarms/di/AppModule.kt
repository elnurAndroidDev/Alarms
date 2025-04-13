package com.isayevapps.alarms.di

import com.isayevapps.alarms.data.repository.RepositoryImpl
import com.isayevapps.alarms.domain.local.LocalDataSource
import com.isayevapps.alarms.domain.repository.Repository
import com.isayevapps.alarms.domain.usecases.GetRingtonesUsecase
import dagger.Binds
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
    fun provideGetRingtonesUsecase(repository: Repository) : GetRingtonesUsecase {
        return GetRingtonesUsecase(repository)
    }


}