package com.example.pab_praktikum01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtEmail = findViewById<EditText>(R.id.edt_login_email)
        val edtPassword = findViewById<EditText>(R.id.edt_login_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val tvForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)
        val tvRegister = findViewById<TextView>(R.id.tv_register)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Email/Username wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Password wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (AuthManager.loginUser(email, password)) {
                Toast.makeText(this, "Login Berhasil! Selamat datang, ${AuthManager.registeredName}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Email atau Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }

        tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
