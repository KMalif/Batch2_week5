package com.example.batch2week5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.batch2week5.databinding.ActivityLoginBinding
import com.example.batch2week5.model.User
import com.example.batch2week5.responses.SingleResponse
import com.example.batch2week5.webservice.APIService
import com.example.batch2week5.webservice.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnLoginAction()
        btnsignUp()
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }

    //function btn sign in action
    private fun btnLoginAction(){
        binding.BtnSignIn.setOnClickListener {
            login()
        }
    }

    //function btn sign up action
    private fun btnsignUp(){
        binding.BtnSignUp.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    //function cek berhasil login
    private fun isLogin(){
        val token = Constant.getToken(this)
        if(!token.equals("UNDEFINED")){
            startActivity(Intent(this,MainActivity::class.java).also {
                finish()
            })
        }
    }

    //function request login ke API
    private  fun login(){
        val email = binding.ETEmail.text.toString()
        val password = binding.ETPassword.text.toString()
        APIService.APIEndpoint().signin(email , password).enqueue(object : Callback<SingleResponse<User>>{
            override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                println(t.message)
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        Constant.setToken(this@LoginActivity,body.data.token)
                        Toast.makeText(applicationContext,"hii ${body.data.username}",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}