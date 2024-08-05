package com.ongo.signal.data.repository.mypage

import com.ongo.signal.data.model.main.BoardDTO
import com.ongo.signal.data.model.my.MyProfileResponse
import retrofit2.Response

interface MyPageRepository {

    suspend fun getMySignal(userId: Long): Response<List<BoardDTO>>

    suspend fun getMyCommentSignal(userId: Long): Response<List<BoardDTO>>

    suspend fun getMyProfile(token: String): Result<MyProfileResponse?>
}