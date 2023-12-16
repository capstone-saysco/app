package com.example.saysco.data.remote.retrofit

import com.example.saysco.data.remote.response.InputAnswerResponse
import com.example.saysco.data.remote.response.InputEssayResponse
import com.example.saysco.data.remote.response.LoginResponse
import com.example.saysco.data.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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

    @FormUrlEncoded
    @POST("essay")
    suspend fun addEssay(
        @Header("Authorization") token: String,
        @Field("user_id") userId: String,
        @Field("question") question: String,
        @Field("key_answer") keyAnswer: String
    ): InputEssayResponse

    @FormUrlEncoded
    @POST("essay/answer/{essay_id}")
    suspend fun addAnswer(
        @Header("Authorization") token: String,
        @Path("essay_id") essayId: String,
        @Field("student_name") studentName: String,
        @Field("student_number") studentNumber: String,
        @Field("answer") studentAnswer: String,
        @Field("score") score: String
    ): InputAnswerResponse


}