package com.example.saysco.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateAnswerResponse(

	@field:SerializedName("updated_data")
	val updatedData: UpdatedData,

	@field:SerializedName("message")
	val message: String
)

data class UpdatedData(
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("student_name")
	val studentName: String,

	@field:SerializedName("score")
	val score: String,

	@field:SerializedName("student_number")
	val studentNumber: String,

	@field:SerializedName("answer")
	val answer: String
)
