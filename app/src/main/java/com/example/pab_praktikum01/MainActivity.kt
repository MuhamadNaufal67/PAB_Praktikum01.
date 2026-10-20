package com.example.pab_praktikum01

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNama = findViewById<EditText>(R.id.edt_nama)
        val rgjk = findViewById<RadioGroup>(R.id.rgJK)
        val spkota = findViewById<Spinner>(R.id.spkota)
        val swMenyetujui = findViewById<Switch>(R.id.swMenyetujui)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)

        val kota = arrayOf("Pilih Kota", "Jakarta", "Bandung", "Surabaya", "Semarang")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kota)
        spkota.adapter = adapter

        btnSubmit.setOnClickListener {
            if (edtNama.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Nama Tidak Boleh Kosong!!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!swMenyetujui.isChecked) {
                Toast.makeText(this, "Anda Harus Menyetujui S&K ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val jk = when (rgjk.checkedRadioButtonId) {
                R.id.rbLaki -> "Laki-Laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "Belum Dipilih"
            }

            DataHolder.nama = edtNama.text.toString()
            DataHolder.gender = jk
            DataHolder.kota = spkota.selectedItem.toString()
            DataHolder.isSubmitted = true

            val intent = Intent(this, DuaActivity::class.java)
            startActivity(intent)
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_form
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_form -> true
                R.id.nav_hasil -> {
                    startActivity(Intent(this, DuaActivity::class.java))
                    true
                }
                R.id.nav_riwayat -> {
                    startActivity(Intent(this, TigaActivity::class.java))
                    true
                }
                R.id.nav_info -> {
                    startActivity(Intent(this, EmpatActivity::class.java))
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
                Toast.makeText(this, "Anda berada di Form Pendaftaran", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_hasil -> {
                startActivity(Intent(this, DuaActivity::class.java))
                true
            }
            R.id.menu_riwayat -> {
                startActivity(Intent(this, TigaActivity::class.java))
                true
            }
            R.id.menu_info -> {
                startActivity(Intent(this, EmpatActivity::class.java))
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
