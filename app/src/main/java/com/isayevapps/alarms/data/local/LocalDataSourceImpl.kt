package com.isayevapps.alarms.data.local

import com.isayevapps.alarms.R
import com.isayevapps.alarms.domain.local.LocalDataSource
import com.isayevapps.alarms.domain.models.Ringtone
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(): LocalDataSource {
    override fun getRingtones(): List<Ringtone> {
        val ringtones = arrayListOf<Ringtone>()
        ringtones.add(Ringtone("Wake up!!!", R.drawable.gym_wakeup, R.raw.gym_wakeup))
        ringtones.add(Ringtone("Get up, stupid shit!!!", R.drawable.getup_stupid, R.raw.getup_stupid))
        return ringtones
    }
}