package com.example.batch2week5.webservice

import com.example.batch2week5.model.User
import com.example.batch2week5.responses.SingleResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface APIEndpoint {

    //Buat Request untuk Register ke API dengan method @POST
    @FormUrlEncoded
    @POST("auth/sign-up")
    fun register(@Field("name")name:String,
                 @Field("username")username :String,
                 @Field("email")email :String,
                 @Field("password")password :String
    ):Call<SingleResponse<User>>




    //Buat Request untuk Login ke API dengan method @POST
    @FormUrlEncoded
    @POST("auth/sign-in")
    fun signin(@Field("email")name:String,
                 @Field("password")username :String

):Call<SingleResponse<User>>
}
