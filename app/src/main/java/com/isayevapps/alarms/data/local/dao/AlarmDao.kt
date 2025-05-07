package com.isayevapps.alarms.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.isayevapps.alarms.data.local.entities.AlarmEntity
import com.isayevapps.alarms.data.local.entities.RingtoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {
    @Query("SELECT * from alarms")
    fun getAlarms(): Flow<List<AlarmEntity>>

    @Insert
    suspend fun addAlarm(alarm: AlarmEntity)

    @Query("SELECT * FROM ringtones")
    fun getAllRingtones(): Flow<List<RingtoneEntity>>
}