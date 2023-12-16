package com.example.saysco.data.remote.response

import com.google.gson.annotations.SerializedName

data class InputAnswerResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class Data(

	@field:SerializedName("student_name")
	val studentName: String,

	@field:SerializedName("score")
	val score: String,

	@field:SerializedName("essay_id")
	val essayId: String,

	@field:SerializedName("student_number")
	val studentNumber: String,

	@field:SerializedName("answer")
	val answer: String,

	@field:SerializedName("id")
	val id: Int
)
