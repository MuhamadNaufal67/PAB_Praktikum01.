package com.example.pab_praktikum01

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class TigaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiga)

        val tvRiwayat = findViewById<TextView>(R.id.tv_riwayat)
        tvRiwayat.text = DataHolder.getFormattedData()

        val btnKembali = findViewById<Button>(R.id.btn_kembali_tiga)
        btnKembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_riwayat
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
                R.id.nav_riwayat -> true
                R.id.nav_info -> {
                    startActivity(Intent(this, EmpatActivity::class.java))
                    finish()
                    true
                }
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
                Toast.makeText(this, "Anda berada di Halaman Riwayat", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_info -> {
                startActivity(Intent(this, EmpatActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
