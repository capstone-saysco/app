package com.example.saysco.data.remote.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AllEssayResponse(

	@field:SerializedName("data")
	val data: List<EssayItem>,

	@field:SerializedName("message")
	val message: String
)

data class EssayItem(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("key_answer")
	val keyAnswer: String? = null,

	@field:SerializedName("user_id")
	val userId: Int = 0,

	@field:SerializedName("id")
	val id: Int = 0
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readInt()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(question)
		parcel.writeString(keyAnswer)
		parcel.writeInt(userId)
		parcel.writeInt(id)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<EssayItem> {
		override fun createFromParcel(parcel: Parcel): EssayItem {
			return EssayItem(parcel)
		}

		override fun newArray(size: Int): Array<EssayItem?> {
			return arrayOfNulls(size)
		}
	}
}
