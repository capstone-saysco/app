package com.example.saysco.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: User
)

data class User(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)
