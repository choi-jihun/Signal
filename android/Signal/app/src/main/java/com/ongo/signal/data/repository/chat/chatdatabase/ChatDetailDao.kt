package com.ongo.signal.data.repository.chat.chatdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ongo.signal.data.model.chat.ChatHomeChildDTO


@Dao
interface ChatDetailDao {
    @Query("SELECT * FROM chat_detail_table where chatId = :id order by messageId desc limit 300")
    suspend fun getAll(id : Long): List<ChatHomeChildDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(chatHomeChildDtos: ChatHomeChildDTO)
}