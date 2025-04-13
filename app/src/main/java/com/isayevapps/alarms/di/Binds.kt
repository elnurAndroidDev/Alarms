package com.isayevapps.alarms.di

import com.isayevapps.alarms.data.local.LocalDataSourceImpl
import com.isayevapps.alarms.data.repository.RepositoryImpl
import com.isayevapps.alarms.domain.local.LocalDataSource
import com.isayevapps.alarms.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Binds {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton // If you want a singleton instance
    abstract fun bindMyRepository(impl: RepositoryImpl): Repository
}