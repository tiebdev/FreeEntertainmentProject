package com.example.freeentproject.ui.activitys

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.freeentproject.R
import com.example.freeentproject.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("app", MODE_PRIVATE)
        setDateIfExist()

        binding.loginBut.setOnClickListener {
            val email = binding.textEmail.text.toString()
            val pass = binding.textPass.text.toString()

            if (login(email,pass)) {
                mainNavigate()
                saveUser(email,pass)
            }
        }

        binding.aboutUs.setOnClickListener {
            MaterialAlertDialogBuilder(it.context)
                .setTitle("Información")
                .setMessage("Free Entertainment Project ha sido creado por Ridouan Tieb, desarrollador" +
                        " de aplicaciones multiplataforma. ")
                .setPositiveButton("Entiendo") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun mainNavigate() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun saveUser(email: String, password: String) {
        val editor = prefs!!.edit()
        if (binding.switchRem.isChecked) {
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("recordar", true)
            editor.apply()
        } else {
            editor.clear()
            editor.putBoolean("recordar", false)
            editor.apply()
        }
    }

    private fun login(email: String, password: String): Boolean {
        var valido = false
        if (!chekEmail(email)) {
            Toast.makeText(this,
                "e-mail no válido. Introduzca un e-mail correcto",
                Toast.LENGTH_SHORT).show()
        } else if (!chekPass(password)) {
            Toast.makeText(this,
                "Password no válida. Debe tener al menos 8 caracteres",
                Toast.LENGTH_SHORT).show()
        } else {
            valido = true
        }
        return valido
    }

    private fun setDateIfExist() {
        val email = prefs!!.getString("email", "")
        val password = prefs!!.getString("password", "")
        val recordar = prefs!!.getBoolean("recordar", false)
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            binding.textEmail.setText(email)
            binding.textPass.setText(password)
            binding.switchRem.isChecked = recordar
        }
    }

    private fun chekEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun chekPass(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length > 7
    }
}