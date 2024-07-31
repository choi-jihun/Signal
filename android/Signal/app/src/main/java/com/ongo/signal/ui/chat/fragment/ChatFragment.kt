package com.ongo.signal.ui.chat.fragment

import android.os.Build.VERSION_CODES.P
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ongo.signal.R
import com.ongo.signal.config.BaseFragment
import com.ongo.signal.data.model.chat.ChatHomeDTO
import com.ongo.signal.data.model.chat.DateConverter
import com.ongo.signal.databinding.FragmentChatBinding
import com.ongo.signal.ui.chat.CustomDialog
import com.ongo.signal.ui.chat.adapter.ChatHomeAdapter
import com.ongo.signal.ui.chat.viewmodels.ChatHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val TAG = "ChatFragment_싸피"

/**
 * 해당 프래그먼트는 채팅 리스트를 보여주는 프래그먼트입니다.
 * 프래그먼트에서 fab 버튼을 누르면 채팅방을 만들 수 있도록 화면이 이동합니다.
 * 또한 채팅 리스트를 클릭할 시 1대1 채팅 화면으로 넘어갑니다.
 *
 */
@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {

    private lateinit var chatHomeAdapter: ChatHomeAdapter
    private val chatViewModel: ChatHomeViewModel by activityViewModels()

    override fun init() {
        binding.apply {

            lifecycleScope.launch {
                while (true) {
                    chatViewModel.loadChats()
                    delay(5000)
                }
            }
            chatViewModel.stompDisconnect()
            chatViewModel.clearMessageList()



//            chatHomeFab.setOnClickListener {
//                chatViewModel.saveChat(
//                    ChatHomeDTO(
//                        0, 1, 2, "last", "null", Date(System.currentTimeMillis())
//                    )
//                )
//            }

            chatHomeAdapter = ChatHomeAdapter(
                chatItemClick = {
                    chatViewModel.chatRoomNumber = it.chat_id
                    chatViewModel.loadDetailList(it.chat_id)
                    findNavController().navigate(R.id.action_chatFragment_to_chatDetailFragment)
                },
                chatItemLongClick = {

                    // 롱 클릭시 커스텀 다이어 로그가 나오게 하여 삭제 여부 및 다른 옵션을 선택할 수 있도록 합니다.
                    CustomDialog.show(requireContext()){
//                        chatViewModel.deleteChat(it)
                    }
                    true
                },
                timeSetting = {item ->
                    chatViewModel.timeSetting(item)
                }

            )
            binding.chatHomeList.adapter = chatHomeAdapter



            lifecycleOwner?.let {
                chatViewModel.liveList.observe(it, Observer { chatList ->
                    chatHomeAdapter.submitList(chatList)
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        chatViewModel.clearMessageList()
    }
}