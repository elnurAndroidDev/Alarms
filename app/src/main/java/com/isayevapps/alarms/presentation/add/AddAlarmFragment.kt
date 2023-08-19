package com.isayevapps.alarms.presentation.add

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.isayevapps.alarms.R
import com.isayevapps.alarms.databinding.FragmentAddAlarmBinding
import com.isayevapps.alarms.presentation.dialogs.RepeatBottomSheet
import com.isayevapps.alarms.presentation.models.Alarm
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.Serializable

class AddAlarmFragment : Fragment() {

    private var _binding: FragmentAddAlarmBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Binding is null"
        }

    private val viewModel: AddAlarmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAlarmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()

        binding.vibrateSwitch.setOnCheckedChangeListener { _, checked ->
            viewModel.setVibrationEnabled(checked)
        }

        binding.repeatLinearLayout.setOnClickListener {
            findNavController().navigate(
                AddAlarmFragmentDirections.openRepeatBottomSheetDialog(
                    frequency = viewModel.getFrequency()
                )
            )
        }

        setFragmentResultListener(RepeatBottomSheet.REQUEST_REPEAT_KEY) { _, bundle ->
            val newRepeat =
                bundle.customGetSerializable<String>(RepeatBottomSheet.BUNDLE_REPEAT_KEY)
            newRepeat?.let {
                viewModel.setRepeat(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.alarm.collectLatest {
                    updateUI(it)
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, T::class.java)
        } else {
            getSerializable(key) as? T
        }
    }

    private fun updateUI(alarm: Alarm) {
        binding.ringtoneNameTextView.text = alarm.ringtone
        binding.repeatFrequencyTextView.text = alarm.repeat
        binding.labelTextView.text = if (alarm.label == "") "Enter label" else alarm.label
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.save_alarm_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.save_alarm -> {
                        Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> true
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}