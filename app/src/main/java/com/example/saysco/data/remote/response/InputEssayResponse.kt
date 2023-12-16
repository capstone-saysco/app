package com.example.saysco.data.remote.response

import com.google.gson.annotations.SerializedName

data class InputEssayResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class Data(

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("key_answer")
	val keyAnswer: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id")
	val id: Int
)
