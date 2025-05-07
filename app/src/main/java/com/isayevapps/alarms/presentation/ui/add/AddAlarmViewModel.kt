package com.isayevapps.alarms.presentation.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.models.Ringtone
import com.isayevapps.alarms.domain.models.Time
import com.isayevapps.alarms.domain.usecases.AddAlarmUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAlarmViewModel @Inject constructor(
    private val addAlarmUsecase: AddAlarmUsecase
) : ViewModel() {
    private val _alarm = MutableStateFlow(Alarm())
    val alarm = _alarm.asStateFlow()


    fun setRingtone(ringtone: Ringtone) {
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

    fun addAlarm() = viewModelScope.launch {
        with(Dispatchers.IO) {
            addAlarmUsecase(_alarm.value)
        }
    }

    fun getRepeat() = _alarm.value.repeat

    fun getLabel() = _alarm.value.label

}