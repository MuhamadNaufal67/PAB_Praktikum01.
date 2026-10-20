package com.example.pab_praktikum01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val edtName = findViewById<EditText>(R.id.edt_reg_name)
        val edtEmail = findViewById<EditText>(R.id.edt_reg_email)
        val edtPassword = findViewById<EditText>(R.id.edt_reg_password)
        val edtConfirmPassword = findViewById<EditText>(R.id.edt_reg_confirm_password)
        val btnRegister = findViewById<Button>(R.id.btn_register)
        val tvLoginLink = findViewById<TextView>(R.id.tv_login_link)

        btnRegister.setOnClickListener {
            val name = edtName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val confirmPass = edtConfirmPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Semua bidang wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPass) {
                Toast.makeText(this, "Konfirmasi Password tidak cocok!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AuthManager.registerUser(email, password, name)
            Toast.makeText(this, "Registrasi Berhasil! Silakan Login.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvLoginLink.setOnClickListener {
            finish()
        }
    }
}
