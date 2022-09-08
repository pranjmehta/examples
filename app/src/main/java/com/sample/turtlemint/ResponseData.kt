package com.sample.turtlemint

import com.google.gson.annotations.SerializedName

data class ResponseData(

    @SerializedName("title")
    var title: String?,

    @SerializedName("body")
    var body: String?,

    @SerializedName("updated_at")
    var updated_at: String?,

    @SerializedName("number")
    var number: Int?,

    @SerializedName("user")
    var user: User?
)
