package com.isayevapps.alarms.presentation.ui.ringtone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.isayevapps.alarms.databinding.FragmentRingtoneBinding

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
        val ringtoneAdapter = RingtoneAdapter(requireContext(), ringtones)
        binding.ringtonesRV.adapter = ringtoneAdapter
    }

    override fun onDestroyView() {
        val adapter = binding.ringtonesRV.adapter as RingtoneAdapter
        adapter.onDestroy()
        super.onDestroyView()
        _binding = null
    }
}