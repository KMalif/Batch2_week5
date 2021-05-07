package com.example.batch2week5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.batch2week5.databinding.ActivityRegisterBinding
import com.example.batch2week5.model.User
import com.example.batch2week5.responses.SingleResponse
import com.example.batch2week5.webservice.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iconback()
        btnregister()

    }

    //function icon back action
    private fun iconback(){
        binding.IconBack.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    //function btn sign up action
    private fun btnregister(){
        binding.BtnSignUp.setOnClickListener {
            register()
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    //function request register
    private fun register(){
        val name = binding.ETName.text.toString()
        val username = binding.ETUsername.text.toString()
        val email = binding.ETEmail.text.toString()
        val password = binding.ETPassword.text.toString()
        APIService.APIEndpoint().register(name, username, email, password)
                .enqueue(object : Callback<SingleResponse<User>>{
                    override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                        println(t.message)
                    }

                    override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                        if(response.isSuccessful){
                            val body = response.body()
                            if(body != null){
                                Toast.makeText(applicationContext,body.msg,Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            val errorBody = response.errorBody().toString()
                            val code = response.code()
                            Log.e("xxxxx", errorBody )
                            Toast.makeText(applicationContext,code.toString(),Toast.LENGTH_SHORT).show()
                        }

                    }

                })
    }
}