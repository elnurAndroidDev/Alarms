package com.isayevapps.alarms.presentation.ui.ringtone

import androidx.lifecycle.ViewModel
import com.isayevapps.alarms.R
import com.isayevapps.alarms.domain.models.Ringtone
import com.isayevapps.alarms.domain.usecases.GetRingtonesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RingtoneViewModel @Inject constructor(private val getRingtonesUsecase: GetRingtonesUsecase) : ViewModel() {

    fun getRingtones(): List<Ringtone> {
        return getRingtonesUsecase()
    }

}