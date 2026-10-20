package com.example.pab_praktikum01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val edtEmail = findViewById<EditText>(R.id.edt_reset_email)
        val edtNewPass = findViewById<EditText>(R.id.edt_reset_new_password)
        val btnReset = findViewById<Button>(R.id.btn_reset_password)
        val tvBackToLogin = findViewById<TextView>(R.id.tv_back_to_login)

        btnReset.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val newPass = edtNewPass.text.toString().trim()

            if (email.isEmpty() || newPass.isEmpty()) {
                Toast.makeText(this, "Email dan Password Baru wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (AuthManager.resetPassword(email, newPass)) {
                Toast.makeText(this, "Password Berhasil Direset! Silakan Login dengan Password Baru.", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Email tidak terdaftar!", Toast.LENGTH_SHORT).show()
            }
        }

        tvBackToLogin.setOnClickListener {
            finish()
        }
    }
}
