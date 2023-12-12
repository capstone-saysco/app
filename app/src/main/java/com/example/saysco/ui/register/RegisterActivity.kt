package com.example.saysco.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saysco.R
import com.example.saysco.databinding.ActivityRegisterBinding
import com.example.saysco.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        goToSignIn()
        setupRegister()
    }

    private fun setupRegister() {
        binding.goToSigninBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun showAlert(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }

    private fun goToSignIn() {
        binding.signupBtn.setOnClickListener {
            val username = binding.edName.text.toString()
            val email = binding.edEmail.text.toString()
            val password = binding.edPw.text.toString()

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                if (password.length >= 8 && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    register(username, email, password)
                } else {
                    showAlert(getString(R.string.not_match))
                }
            } else {
                showAlert(getString(R.string.empty_ed))
            }
        }
    }

    private fun register(username: String, email: String, password: String) {
        showAlert("Username : $username Email : $email password : $password")
        startActivity(Intent(this, LoginActivity::class.java))
    }
}