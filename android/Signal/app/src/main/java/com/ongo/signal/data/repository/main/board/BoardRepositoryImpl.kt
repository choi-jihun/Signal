package com.ongo.signal.data.repository.main.board

import com.ongo.signal.data.model.main.BoardDTO
import com.ongo.signal.data.model.main.BoardImagesDTO
import com.ongo.signal.data.model.main.BoardRequestDTO
import com.ongo.signal.data.model.main.UpdateBoardDTO
import com.ongo.signal.network.MainApi
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BoardRepositoryImpl @Inject constructor(private val mainApi: MainApi) : BoardRepository {
    override suspend fun writeBoard(boardRequestDTO: BoardRequestDTO): Response<BoardDTO> {
        return mainApi.writeBoard(boardRequestDTO)
    }

    override suspend fun readBoard(pageNum: Int, limit: Int): Response<List<BoardDTO>> {
        return mainApi.readBoard(pageNum, limit)
    }

    override suspend fun readBoardById(boardId: Long): Response<BoardDTO> {
        return mainApi.readBoardById(boardId)
    }

    override suspend fun updateBoard(
        boardId: Long,
        updateBoardDTO: UpdateBoardDTO
    ): Response<BoardDTO> {
        return mainApi.updateBoard(boardId, updateBoardDTO)
    }

    override suspend fun deleteBoard(boardId: Long): Response<ResponseBody> {
        return mainApi.deleteBoard(boardId)
    }

//    override suspend fun uploadImage(
//        boardId: Long,
//        image: MultipartBody.Part
//    ): Response<ResponseBody> {
//        return mainApi.uploadImage(boardId, image)
//    }

    override suspend fun boardLike(boardId: Long): Response<Long> {
        return mainApi.boardLike(boardId)
    }

//    override suspend fun getBoardImages(): Response<BoardImagesDTO> {
//        return mainApi.getBoardImages()
//    }

    override suspend fun searchBoard(keyword: String): Response<List<BoardDTO>> {
        return mainApi.searchBoard(keyword)
    }

    override suspend fun getHotSignal(): Response<List<BoardDTO>> {
        return mainApi.getHotSignal()
    }

    override suspend fun getRecentSignalByTag(
        tag: String,
        page: Int,
        limit: Int
    ): Response<List<BoardDTO>> {
        return mainApi.getRecentSignalByTag(tag, page, limit)
    }

    override suspend fun getHotSignalByTag(
        tag: String,
        page: Int,
        limit: Int
    ): Response<List<BoardDTO>> {
        return mainApi.getHotSignalByTag(tag, page, limit)
    }
}