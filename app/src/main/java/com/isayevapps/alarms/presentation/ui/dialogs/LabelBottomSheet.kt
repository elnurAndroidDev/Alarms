package com.isayevapps.alarms.presentation.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.isayevapps.alarms.databinding.LabelBottomSheetBinding

class LabelBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: LabelBottomSheetBinding

    private val args: LabelBottomSheetArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LabelBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = args.label
        binding.labelEditText.setText(text)
        binding.cancelLabelButton.setOnClickListener {
            dismiss()
        }
        binding.okLabelButton.setOnClickListener {
            val newLabel = binding.labelEditText.text.toString()
            setFragmentResult(
                REQUEST_LABEL_KEY,
                bundleOf(BUNDLE_LABEL_KEY to newLabel)
            )
            dismiss()
        }
    }

    companion object {
        const val REQUEST_LABEL_KEY = "REQUEST_KEY_LABEL"
        const val BUNDLE_LABEL_KEY = "BUNDLE_KEY_LABEL"
    }

}