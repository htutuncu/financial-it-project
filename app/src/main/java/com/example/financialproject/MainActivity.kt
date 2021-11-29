package com.example.financialproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.financialproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val dbHelper : DBHelper = DBHelper(this)
    lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    fun signIn(view : View) : Unit{
        val userName = binding.tilMail.editText.toString()
        val password = binding.tilPassword.editText.toString()

        if (userName == "" || password =="") {
            Toast.makeText(this,"Lütfen tüm alanları doldurun.", Toast.LENGTH_LONG).show()
        }
        else {
            if (dbHelper.checkUserName(userName)) {
                Toast.makeText(this,"Giriş başarılı.", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MoviesActivity::class.java))
            }
            else {
                Toast.makeText(this,"Hatalı giriş. Lütfen tekrar deneyiniz.",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signUp(view : View) : Unit{
        var userName = binding.tilMail.editText.toString()
        var password = binding.tilPassword.editText.toString()

        if (userName == "" || password == "") {
            Toast.makeText(this,"Lütfen tüm alanları doldurun.", Toast.LENGTH_LONG).show()
        }
        else {
            if (dbHelper.checkUserName(userName)) {
                Toast.makeText(this,"Kullanıcı zaten kayıtlı.", Toast.LENGTH_LONG).show()
            }
            else {
                val bool = dbHelper.insertData(userName,password)
                if (bool) {
                    Toast.makeText(this,"Kayıt başarılı.", Toast.LENGTH_LONG).show()
                    binding.tilMail.editText?.setText("")
                    binding.tilPassword.editText?.setText("")
                }
                else
                    Toast.makeText(this,"Kayıt edilemedi.", Toast.LENGTH_LONG).show()
            }
        }
    }
}