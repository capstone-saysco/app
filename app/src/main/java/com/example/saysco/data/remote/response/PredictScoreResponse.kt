package com.example.saysco.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictScoreResponse(

	@field:SerializedName("data")
	val data: DataScore,

	@field:SerializedName("message")
	val message: String
)

data class DataScore(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("score_result")
	val scoreResult: Int
)
