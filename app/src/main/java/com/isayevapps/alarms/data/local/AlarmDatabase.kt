package com.isayevapps.alarms.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isayevapps.alarms.data.local.dao.AlarmDao
import com.isayevapps.alarms.data.local.entities.AlarmEntity
import com.isayevapps.alarms.data.local.entities.RingtoneEntity

@Database(entities = [AlarmEntity::class, RingtoneEntity::class], version = 1, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    companion object {
        @Volatile
        private var Instance: AlarmDatabase? = null

        fun getDatabase(context: Context): AlarmDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AlarmDatabase::class.java, "alarm_database")
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }

}