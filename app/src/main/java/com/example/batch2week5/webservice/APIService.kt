package com.example.batch2week5.webservice

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIService {
    companion object{
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        private fun getClient(): Retrofit{
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }

        fun APIEndpoint():APIEndpoint = getClient().create(APIEndpoint::class.java)
    }

}

class Constant{
    companion object{
        const val BASE_URL = "https://backend-book-collection.herokuapp.com/ "

        //Function untuk menyimpan token ke dalam shared preferences
        fun setToken(context : Context,token : String){
            val sharepheference = context.getSharedPreferences("TOKEN",Context.MODE_PRIVATE)
            sharepheference.edit().apply {
             putString("TOKEN",token)
                apply()
            }
        }


        //Function untuk mengambil token dari shared preferences
        fun getToken(context: Context):String{
            val sharedpref = context.getSharedPreferences("TOKEN",Context.MODE_PRIVATE)
            val token = sharedpref.getString("TOKEN","UNDEFINED")
            return token!!
        }


        //function untul menghapus token dari shared preferences
        fun clearToken(context : Context){
            val sharedpref = context.getSharedPreferences("TOKEN",Context.MODE_PRIVATE)
            sharedpref.edit().clear().apply()
        }


    }
}