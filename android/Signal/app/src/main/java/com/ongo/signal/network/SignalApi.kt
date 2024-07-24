package com.ongo.signal.network

import com.ongo.signal.data.model.match.MatchRegistrationRequest
import com.ongo.signal.data.model.match.MatchRegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface SignalApi {
    @Headers(
        "Content-Type: application/json",
        "accesstoken: asda13"
    )
    @GET("v1/main/gogo")
    suspend fun getMainPost(id: Int): Response<Int>

    @POST("location")
    suspend fun postMatchRegistration(
        @Body request: MatchRegistrationRequest
    ): Response<MatchRegistrationResponse>

    @DELETE("location/user")
    suspend fun deleteMatchRegistration(
        @Query("userId") userId: Long
    ) : Response<Int>
}