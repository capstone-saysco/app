package com.example.saysco.data.remote.response

import com.google.gson.annotations.SerializedName

data class AllAnswersResponse(

	@field:SerializedName("data")
	val data: List<AnswerItem>,

	@field:SerializedName("message")
	val message: String
)

data class AnswerItem(

	@field:SerializedName("student_name")
	val studentName: String,

	@field:SerializedName("score")
	val score: Int,

	@field:SerializedName("essay_id")
	val essayId: Int,

	@field:SerializedName("student_number")
	val studentNumber: Int,

	@field:SerializedName("answer")
	val answer: String,

	@field:SerializedName("id")
	val id: Int
)
