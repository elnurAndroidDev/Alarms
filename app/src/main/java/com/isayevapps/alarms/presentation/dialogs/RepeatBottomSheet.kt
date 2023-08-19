package com.isayevapps.alarms.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.isayevapps.alarms.R
import com.isayevapps.alarms.databinding.RepeatBottomSheetBinding

class RepeatBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: RepeatBottomSheetBinding

    private val args: RepeatBottomSheetArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepeatBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val frequency = args.frequency
        activateOption(frequency)
        binding.onceLinearLayout.setOnClickListener {
            activateOption("Once")
            dismiss()
        }
        binding.dailyLinearLayout.setOnClickListener {
            activateOption("Daily")
            dismiss()
        }
        binding.weekdaysLinearLayout.setOnClickListener {
            activateOption("Mon to Fri")
            dismiss()
        }
        binding.customLinearLayout.setOnClickListener {
            activateOption("Custom")
            dismiss()
        }
    }

    private fun activateOption(frequency: String) {
        val activeDrawable =
            ResourcesCompat.getDrawable(resources, R.drawable.label_group_shape, null)
        binding.onceLinearLayout.background = if (frequency == "Once") activeDrawable else null
        binding.onceTick.visibility = if (frequency == "Once") View.VISIBLE else View.INVISIBLE

        binding.dailyLinearLayout.background = if (frequency == "Daily") activeDrawable else null
        binding.dailyTick.visibility = if (frequency == "Daily") View.VISIBLE else View.INVISIBLE

        binding.weekdaysLinearLayout.background =
            if (frequency == "Mon to Fri") activeDrawable else null
        binding.weekdaysTick.visibility =
            if (frequency == "Mon to Fri") View.VISIBLE else View.INVISIBLE

        binding.customLinearLayout.background = if (frequency == "Custom") activeDrawable else null
        binding.customTick.visibility = if (frequency == "Custom") View.VISIBLE else View.INVISIBLE

        setFragmentResult(
            REQUEST_REPEAT_KEY,
            bundleOf(BUNDLE_REPEAT_KEY to frequency)
        )
    }

    companion object {
        const val REQUEST_REPEAT_KEY = "REQUEST_KEY_REPEAT"
        const val BUNDLE_REPEAT_KEY = "BUNDLE_KEY_REPEAT"
    }
}