package com.example.pab_praktikum01

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.concurrent.thread

class DuaActivity : AppCompatActivity() {

    private var progress = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dua)

        val pbLoading = findViewById<ProgressBar>(R.id.pbloading)
        val tvHasil = findViewById<TextView>(R.id.tv_hasil)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        val data = DataHolder.getFormattedData()

        pbLoading.visibility = View.VISIBLE
        tvHasil.visibility = View.GONE

        thread {
            while (progress < 100) {
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progress++
                val p = progress
                handler.post { pbLoading.progress = p }
            }
            handler.post {
                pbLoading.visibility = View.GONE
                tvHasil.visibility = View.VISIBLE
                tvHasil.text = data
            }
        }

        btnKembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_hasil
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_form -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_hasil -> true
                R.id.nav_riwayat -> {
                    startActivity(Intent(this, TigaActivity::class.java))
                    finish()
                    true
                }
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
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
                true
            }
            R.id.menu_hasil -> {
                Toast.makeText(this, "Anda berada di Halaman Hasil", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_riwayat -> {
                startActivity(Intent(this, TigaActivity::class.java))
                finish()
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
