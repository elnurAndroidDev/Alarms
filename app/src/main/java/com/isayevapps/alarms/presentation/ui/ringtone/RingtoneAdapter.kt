package com.isayevapps.alarms.presentation.ui.ringtone

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.isayevapps.alarms.R
import com.isayevapps.alarms.databinding.ItemRingtoneBinding

class RingtoneAdapter(
    private val context: Context,
    private val ringtones: List<RingtoneRVItem>,
    private val setFragmentResult: (RingtoneRVItem) -> Unit
) :
    RecyclerView.Adapter<RingtoneAdapter.RingtoneViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentRingtonePos = -1

    inner class RingtoneViewHolder(private val binding: ItemRingtoneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ringtone: RingtoneRVItem, pos: Int) {
            binding.ringtoneNameTextView.text = ringtone.name
            binding.ringtoneImageView.setImageResource(ringtone.image)

            if (!ringtone.selected) {
                binding.ringtoneContainer.background = null
                binding.soundAnimView.pauseAnimation()
                binding.soundAnimView.visibility = View.INVISIBLE
            }

            binding.ringtoneContainer.setOnClickListener {
                if (currentRingtonePos == pos) {
                    val isPlaying = mediaPlayer?.isPlaying ?: false
                    if (isPlaying) {
                        mediaPlayer?.stop()
                        binding.soundAnimView.visibility = View.INVISIBLE
                        binding.soundAnimView.pauseAnimation()
                    } else {
                        mediaPlayer = MediaPlayer.create(context, ringtone.ringtone)
                        mediaPlayer?.setOnCompletionListener {
                            binding.soundAnimView.visibility = View.INVISIBLE
                            binding.soundAnimView.pauseAnimation()
                        }
                        mediaPlayer?.start()
                        binding.soundAnimView.visibility = View.VISIBLE
                        binding.soundAnimView.playAnimation()
                    }
                } else {
                    mediaPlayer?.stop()
                    mediaPlayer = MediaPlayer.create(context, ringtone.ringtone)
                    mediaPlayer?.setOnCompletionListener {
                        binding.soundAnimView.visibility = View.INVISIBLE
                        binding.soundAnimView.pauseAnimation()
                    }
                    mediaPlayer?.start()
                    binding.soundAnimView.visibility = View.VISIBLE
                    binding.soundAnimView.playAnimation()
                    binding.ringtoneContainer.background =
                        ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.ringtone_selected_shape,
                            null
                        )
                    if (currentRingtonePos != -1) {
                        ringtones[currentRingtonePos].selected = false
                        notifyItemChanged(currentRingtonePos)
                    }
                    currentRingtonePos = pos
                    ringtones[currentRingtonePos].selected = true
                    setFragmentResult(ringtone)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RingtoneViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRingtoneBinding.inflate(inflater, parent, false)
        return RingtoneViewHolder(binding)
    }

    override fun getItemCount() = ringtones.size

    override fun onBindViewHolder(holder: RingtoneViewHolder, position: Int) {
        val ringtone = ringtones[position]
        holder.bind(ringtone, position)
    }

    fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer = null
    }
}