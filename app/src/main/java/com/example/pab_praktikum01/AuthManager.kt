package com.example.pab_praktikum01

object AuthManager {
    // Akun default untuk pengujian
    var registeredEmail: String = "admin@gmail.com"
    var registeredPass: String = "123456"
    var registeredName: String = "Admin"
    var isLoggedIn: Boolean = false

    fun registerUser(email: String, pass: String, name: String) {
        registeredEmail = email
        registeredPass = pass
        registeredName = name
    }

    fun loginUser(email: String, pass: String): Boolean {
        if (email.equals(registeredEmail, ignoreCase = true) && pass == registeredPass) {
            isLoggedIn = true
            return true
        }
        return false
    }

    fun resetPassword(email: String, newPass: String = "123456"): Boolean {
        if (email.equals(registeredEmail, ignoreCase = true)) {
            registeredPass = newPass
            return true
        }
        return false
    }
}
