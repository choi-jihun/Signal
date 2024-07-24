package com.ongo.signal.ui.main.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongo.signal.R
import com.ongo.signal.config.BaseFragment
import com.ongo.signal.databinding.FragmentPostBinding
import com.ongo.signal.ui.main.MainViewModel
import com.ongo.signal.ui.main.adapter.ChipAdapter
import com.ongo.signal.ui.main.adapter.CommentAdapter
import com.ongo.signal.util.PopupMenuHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    private lateinit var commentAdapter: CommentAdapter
    private lateinit var chipAdapter: ChipAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun init() {
        commentAdapter = CommentAdapter()
        chipAdapter = ChipAdapter()

        binding.rvComment.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = commentAdapter
        }

        binding.rvChip.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = chipAdapter
        }

        lifecycleScope.launch {
            viewModel.selectedPost.collectLatest { post ->
                post?.let {
                    binding.post = it
                    chipAdapter.submitList(it.tags)
                    commentAdapter.submitList(it.comment)
                }
            }
        }

        binding.fragment = this
    }

    fun showPopupMenu(view: View) {
        PopupMenuHelper.showPopupMenu(requireContext(), view, R.menu.popup_menu) { item ->
            when (item.itemId) {
                R.id.action_edit -> {
                    makeToast("수정")
                    true
                }

                R.id.action_delete -> {
                    makeToast("삭제")
                    true
                }

                else -> false
            }
        }
    }

    fun onProfileClick() {
        findNavController().navigate(R.id.action_postFragment_to_reviewFragment)
    }

}