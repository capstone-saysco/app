package com.example.saysco.data.remote.retrofit

import com.example.saysco.data.remote.response.InputAnswerResponse
import com.example.saysco.data.remote.response.InputEssayResponse
import com.example.saysco.data.remote.response.LoginResponse
import com.example.saysco.data.remote.response.RegisterResponse
import com.example.saysco.data.remote.response.UserResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("user")
    suspend fun getUser() : UserResponse

    @FormUrlEncoded
    @POST("essay")
    suspend fun addEssay(
        @Field("user_id") userId: String,
        @Field("question") question: String,
        @Field("key_answer") keyAnswer: String
    ): InputEssayResponse

    @FormUrlEncoded
    @POST("essay/answer/{essay_id}")
    suspend fun addAnswer(
        @Path("essay_id") essayId: String,
        @Field("student_name") studentName: String,
        @Field("student_number") studentNumber: String,
        @Field("answer") answer: String,
        @Field("score") score: String
    ): InputAnswerResponse


}