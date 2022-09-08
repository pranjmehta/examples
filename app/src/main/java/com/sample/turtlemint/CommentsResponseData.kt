package com.sample.turtlemint

import com.google.gson.annotations.SerializedName

data class CommentsResponseData(

    @SerializedName("body")
    var body: String?,

    @SerializedName("updated_at")
    var updated_at: String?,

    @SerializedName("user")
    var user: User?
)
