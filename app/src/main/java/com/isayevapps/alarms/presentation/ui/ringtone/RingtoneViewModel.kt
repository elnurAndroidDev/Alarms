package com.isayevapps.alarms.presentation.ui.ringtone

import androidx.lifecycle.ViewModel
import com.isayevapps.alarms.R
import com.isayevapps.alarms.presentation.models.Ringtone

class RingtoneViewModel : ViewModel() {

    fun getRingtones(): List<Ringtone> {
        val ringtones = arrayListOf<Ringtone>()
        ringtones.add(Ringtone("Wake up!!!", R.drawable.gym_wakeup, R.raw.gym_wakeup))
        ringtones.add(Ringtone("Get up, stupid shit!!!", R.drawable.getup_stupid, R.raw.getup_stupid))
        return ringtones
    }

}