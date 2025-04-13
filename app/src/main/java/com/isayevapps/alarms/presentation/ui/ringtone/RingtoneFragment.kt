package com.isayevapps.alarms.presentation.ui.ringtone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.isayevapps.alarms.databinding.FragmentRingtoneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RingtoneFragment : Fragment() {

    private var _binding: FragmentRingtoneBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Binding is null"
        }

    private val viewModel: RingtoneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRingtoneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.ringtonesRV.layoutManager = gridLayoutManager

        val ringtones = viewModel.getRingtones().map { it.toRingtoneRVItem() }
        val ringtoneAdapter = RingtoneAdapter(requireContext(), ringtones) { ringtoneItem ->
            setFragmentResult(
                REQUEST_RINGTONE_KEY,
                bundleOf(BUNDLE_RINGTONE_KEY to ringtoneItem.toRingtone())
            )
        }
        binding.ringtonesRV.adapter = ringtoneAdapter
    }

    override fun onDestroyView() {
        val adapter = binding.ringtonesRV.adapter as RingtoneAdapter
        adapter.onDestroy()
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val REQUEST_RINGTONE_KEY = "REQUEST_KEY_RINGTONE"
        const val BUNDLE_RINGTONE_KEY = "BUNDLE_KEY_RINGTONE"
    }
}