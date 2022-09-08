package com.sample.turtlemint

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {

    @GET("issues")
    fun getGitIssues(): Call<List<ResponseData>>

    @GET("issues/{number}/comments")
    fun getComments(@Path("number") number: Int): Call<List<CommentsResponseData>>

    companion object {

        const val GIT_ISSUES = "https://api.github.com/repos/square/okhttp/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GIT_ISSUES)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}