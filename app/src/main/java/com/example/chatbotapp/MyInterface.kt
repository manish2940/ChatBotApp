package com.example.chatbotapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface MyInterface {
    @GET
    fun getMessage(@Url url: String) : Call<Module1>
}