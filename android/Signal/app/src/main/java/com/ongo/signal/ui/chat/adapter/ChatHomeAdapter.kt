package com.ongo.signal.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ongo.signal.data.model.chat.ChatHomeDTO
import com.ongo.signal.databinding.ChatItemListBinding

private const val TAG = "ChatHomeAdapter_싸피"
class ChatHomeAdapter(
    private val chatItemClick: (item : ChatHomeDTO) -> Unit,
    private val chatItemLongClick: (item : ChatHomeDTO) -> Boolean,
    private val timeSetting: (item: String) -> String
): ListAdapter<ChatHomeDTO, ChatHomeAdapter.ChatHomeListHolder>(diffUtil) {
    inner class ChatHomeListHolder(val binding: ChatItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ChatHomeDTO){

            binding.chatItemTitle.text = item.toName

            binding.content.text = item.lastMessage

            binding.Time.text = timeSetting(item.sendAt.toString())

            binding.chatHomeCl.setOnClickListener {
                chatItemClick(item)
            }

            binding.chatHomeCl.setOnLongClickListener {
                chatItemLongClick(item)
            }

            binding.Alarm.text = "1"
        }
    }



    override fun onBindViewHolder(holder: ChatHomeListHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHomeListHolder {
        return ChatHomeListHolder(
            ChatItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChatHomeDTO>() {
            override fun areItemsTheSame(oldItem: ChatHomeDTO, newItem: ChatHomeDTO): Boolean {
                return oldItem.chatId == newItem.chatId
            }

            override fun areContentsTheSame(oldItem: ChatHomeDTO, newItem: ChatHomeDTO): Boolean {

                return oldItem == newItem
            }
        }
    }
}