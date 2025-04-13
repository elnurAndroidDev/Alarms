package com.isayevapps.alarms.presentation.ui.add

import android.util.Log
import androidx.lifecycle.ViewModel
import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.models.Ringtone
import com.isayevapps.alarms.domain.models.Time
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddAlarmViewModel : ViewModel() {
    private val _alarm = MutableStateFlow(Alarm())
    val alarm = _alarm.asStateFlow()


    fun setRingtone(ringtone: Ringtone) {
        Log.d("MyTaggg", ringtone.name)
        _alarm.update { oldValue ->
            oldValue.copy(ringtone = ringtone)
        }
    }

    fun setLabel(label: String) {
        _alarm.update { oldValue ->
            oldValue.copy(label = label)
        }
    }

    fun setVibrationEnabled(checked: Boolean) {
        _alarm.update { oldValue ->
            oldValue.copy(isVibrationEnabled = checked)
        }
    }

    fun setRepeat(frequency: String) {
        _alarm.update { oldValue ->
            oldValue.copy(repeat = frequency)
        }
    }

    fun setTime(hours: Int, mins: Int) {
        val time = Time(hours, mins)
        _alarm.update { oldValue ->
            oldValue.copy(time = time)
        }
    }

    fun getFrequency() = _alarm.value.repeat

    fun getLabel() = _alarm.value.label

}