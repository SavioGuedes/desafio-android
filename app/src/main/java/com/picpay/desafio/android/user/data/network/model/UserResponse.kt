package com.picpay.desafio.android.user.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("img") val img: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("username") val username: String? = ""
) : Parcelable