package com.financial.financialproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.financial.financialproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val dbHelper : DBHelper = DBHelper(this)
    lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        if ( currentUser!=null) {
            startActivity(Intent(this,MoviesActivity::class.java))
        }

    }


    fun signIn(view : View) : Unit{
        val userName = binding.tilMail.editText?.text.toString()
        val password = binding.tilPassword.editText?.text.toString()

        if (userName == "" || password =="") {
            Toast.makeText(this,"Lütfen tüm alanları doldurun.", Toast.LENGTH_LONG).show()
        }
        else {
//            if (dbHelper.checkUserName(userName)) {
//                Toast.makeText(this,"Giriş başarılı.", Toast.LENGTH_LONG).show()
//                startActivity(Intent(this,MoviesActivity::class.java))
//            }
//            else {
//                Toast.makeText(this,"Hatalı giriş. Lütfen tekrar deneyiniz.",Toast.LENGTH_LONG).show()
//            }

            auth.signInWithEmailAndPassword(userName,password).addOnSuccessListener {
                startActivity(Intent(this,MoviesActivity::class.java))
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signUp(view : View) : Unit{
        var userName = binding.tilMail.editText?.text.toString()
        var password = binding.tilPassword.editText?.text.toString()

        if (userName == "" || password == "") {
            Toast.makeText(this,"Lütfen tüm alanları doldurun.", Toast.LENGTH_LONG).show()
        }
        else {
//            if (dbHelper.checkUserName(userName)) {
//                Toast.makeText(this,"Kullanıcı zaten kayıtlı.", Toast.LENGTH_LONG).show()
//            }
//            else {
//                val bool = dbHelper.insertData(userName,password)
//                if (bool) {
//                    Toast.makeText(this,"Kayıt başarılı.", Toast.LENGTH_LONG).show()
//                    binding.tilMail.editText?.setText("")
//                    binding.tilPassword.editText?.setText("")
//                }
//                else
//                    Toast.makeText(this,"Kayıt edilemedi.", Toast.LENGTH_LONG).show()
//            }

            auth.createUserWithEmailAndPassword(userName,password).addOnSuccessListener {
                // success
                Toast.makeText(this,"Kayıt başarılı.", Toast.LENGTH_LONG).show()
                    binding.tilMail.editText?.setText("")
                    binding.tilPassword.editText?.setText("")
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }

        }
    }
}