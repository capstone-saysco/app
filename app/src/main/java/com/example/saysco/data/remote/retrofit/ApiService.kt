package com.example.saysco.data.remote.retrofit

import com.example.saysco.data.remote.response.AllAnswersResponse
import com.example.saysco.data.remote.response.AllEssayResponse
import com.example.saysco.data.remote.response.InputAnswerResponse
import com.example.saysco.data.remote.response.InputEssayResponse
import com.example.saysco.data.remote.response.LoginResponse
import com.example.saysco.data.remote.response.LogoutResponse
import com.example.saysco.data.remote.response.PredictScoreResponse
import com.example.saysco.data.remote.response.RegisterResponse
import com.example.saysco.data.remote.response.UpdateAnswerResponse
import com.example.saysco.data.remote.response.UserResponse
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @FormUrlEncoded
    @PUT("essay/answer/{answer_id}")
    suspend fun updateAnswer(
        @Path("answer_id") id: String,
        @Field("student_name") studentName: String,
        @Field("student_number") studentNumber: String,
        @Field("answer") answer: String,
        @Field("score") score: String
    ): UpdateAnswerResponse

    @FormUrlEncoded
    @POST("predict-score")
    suspend fun predictScore(
        @Field("answer_id") id: String,
        @Field("answer") answer: String,
    ): PredictScoreResponse

    @DELETE("logout")
    suspend fun logout(): LogoutResponse
    @GET("essay")
    suspend fun getAllEssays(): AllEssayResponse

//    @FormUrlEncoded
    @GET("essay/answer/{answer_id}")
    suspend fun getAnswer(
        @Path("answer_id") id: String,
    ): AllAnswersResponse
}