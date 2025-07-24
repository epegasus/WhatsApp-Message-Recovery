package dev.pegasus.whatsapp.message.recovery.presentation.adapters

import android.graphics.BitmapFactory
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.pegasus.whatsapp.message.recovery.R
import dev.pegasus.whatsapp.message.recovery.data.entities.ItemDbMessage
import dev.pegasus.whatsapp.message.recovery.databinding.ItemMessageBinding

/**
 * Created by: Sohaib Ahmed
 * Date: 7/18/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class AdapterMessages : ListAdapter<ItemDbMessage, AdapterMessages.CustomViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CustomViewHolder(private val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemDbMessage) = with(binding) {
            mtvSender.text = item.user
            mtvMessage.text = if (item.isDeleted) "🗑️ Message deleted" else item.message

            mtvTime.text = DateUtils.getRelativeTimeSpanString(item.time, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS)

            // Show Group badge only if it's a group message
            mtvGroup.visibility = if (item.isGroup) View.VISIBLE else View.GONE

            // Handle icon from ByteArray
            item.iconBytes?.let {
                val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
                ifvAppIcon.setImageBitmap(bmp)
            } ?: ifvAppIcon.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ItemDbMessage>() {
        override fun areItemsTheSame(oldItem: ItemDbMessage, newItem: ItemDbMessage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemDbMessage, newItem: ItemDbMessage): Boolean {
            return oldItem == newItem
        }
    }
}
