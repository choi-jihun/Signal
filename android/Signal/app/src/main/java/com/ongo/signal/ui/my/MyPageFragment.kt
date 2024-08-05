package com.ongo.signal.ui.my

import android.content.Intent
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ongo.signal.R
import com.ongo.signal.config.BaseFragment
import com.ongo.signal.config.UserSession
import com.ongo.signal.databinding.FragmentMypageBinding
import com.ongo.signal.ui.LoginActivity
import com.ongo.signal.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel: MyPageViewModel by viewModels()

    override fun init() {
        getMyProfile()
        initViews()
        binding.fragment = this
    }

    private fun getMyProfile() {
        UserSession.accessToken?.let {
            viewModel.getMyProfile(it) { myProfileData ->
                with(binding) {
                    if (myProfileData.profileImage == "null") {
                        ivProfile.setImageResource(R.drawable.basic_profile)
                    } else {
                        //TODO place홀더 로딩 이미지 찾아보기
                        Glide.with(requireActivity())
                            .load(myProfileData.profileImage)
                            .circleCrop()
                            .into(ivProfile)
                    }

                    tvUsername.text = myProfileData.name
                }
            }
        }
    }

    private fun initViews() {
        binding.ivLogout.setOnClickListener {
            UserSession.refreshToken?.let { refreshToken ->
                viewModel.sendLogout(refreshToken) { successFlag ->
                    if (successFlag == 1) {
                        makeToast("로그아웃 되었습니다.")
                        goToLoginActivity()
                    }
                }
            }
        }
        binding.tvLogout.setOnClickListener {
            UserSession.refreshToken?.let { refreshToken ->
                viewModel.sendLogout(refreshToken) { successFlag ->
                    if (successFlag == 1) {
                        makeToast("로그아웃 되었습니다.")
                        goToLoginActivity()
                    }
                }
            }
        }
    }

    fun goToProfileEdit() {
        parentFragmentManager.commit {
            (requireActivity() as MainActivity).hideBottomNavigation()
            findNavController().navigate(MyPageFragmentDirections.actionMyPageFragmentToProfileEditFragment(argNumber = 111))
        }
    }

    fun goToMySignal() {
        parentFragmentManager.commit {
            (requireActivity() as MainActivity).hideBottomNavigation()
            findNavController().navigate(R.id.action_myPageFragment_to_mySignalFragment)
        }
    }

    fun goToMyCommentSignal() {
        parentFragmentManager.commit {
            (requireActivity() as MainActivity).hideBottomNavigation()
            findNavController().navigate(R.id.action_myPageFragment_to_myCommentSignalFragment)
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}