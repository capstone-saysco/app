package com.example.saysco.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saysco.R
import com.example.saysco.databinding.ActivityLoginBinding
import com.example.saysco.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        goToSignUp()
        setupLogin()
    }

    private fun setupLogin() {
        binding.signinBtn.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPw.text.toString()

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                if (password.length >= 8 && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    login(email, password)
                } else {
                    showAlert(getString(R.string.not_match))
                }
            } else {
                showAlert(getString(R.string.empty_ed))
            }
        }
    }

    private fun showAlert(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }

    private fun login(email: String, password: String) {
        showAlert("Email : $email password : $password")
    }

    private fun goToSignUp() {
        binding.goToSignupBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


}