package com.isayevapps.alarms.di

import android.content.Context
import com.isayevapps.alarms.data.local.AlarmDatabase
import com.isayevapps.alarms.data.local.dao.AlarmDao
import com.isayevapps.alarms.domain.repository.Repository
import com.isayevapps.alarms.domain.usecases.AddAlarmUsecase
import com.isayevapps.alarms.domain.usecases.GetAlarmsUsecase
import com.isayevapps.alarms.domain.usecases.GetRingtonesUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AlarmDatabase {
        return AlarmDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAlarmDao(alarmDatabase: AlarmDatabase): AlarmDao {
        return alarmDatabase.alarmDao()
    }

    @Provides
    @Singleton
    fun provideGetRingtonesUsecase(repository: Repository): GetRingtonesUsecase {
        return GetRingtonesUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAlarmsUsecase(repository: Repository): GetAlarmsUsecase {
        return GetAlarmsUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideAddAlarmUsecase(repository: Repository): AddAlarmUsecase {
        return AddAlarmUsecase(repository)
    }


}