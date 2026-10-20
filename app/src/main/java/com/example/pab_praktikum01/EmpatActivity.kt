package com.example.pab_praktikum01

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class EmpatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empat)

        val btnKembali = findViewById<Button>(R.id.btn_kembali_empat)
        btnKembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            performLogout()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_info
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_form -> {
                    startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                    true
                }
                R.id.nav_hasil -> {
                    startActivity(Intent(this, DuaActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_riwayat -> {
                    startActivity(Intent(this, TigaActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_info -> true
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_form -> {
                startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
                true
            }
            R.id.menu_hasil -> {
                startActivity(Intent(this, DuaActivity::class.java))
                finish()
                true
            }
            R.id.menu_riwayat -> {
                startActivity(Intent(this, TigaActivity::class.java))
                finish()
                true
            }
            R.id.menu_info -> {
                Toast.makeText(this, "Anda berada di Info Aplikasi", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_logout -> {
                performLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun performLogout() {
        AuthManager.isLoggedIn = false
        Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}
