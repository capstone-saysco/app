package com.example.saysco.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.saysco.R
import com.example.saysco.data.Result
import com.example.saysco.databinding.ActivityRegisterBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

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
//        showAlert("Username : $username Email : $email password : $password")
//        startActivity(Intent(this, LoginActivity::class.java))

        viewModel.register(username, email, password).observe(this) {
            if (it != null) {
                when (it) {
                    Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Success -> {
                        showLoading(false)
                        with(binding) {
                            edName.text?.clear()
                            edEmail.text?.clear()
                            edPw.text?.clear()
                        }
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }

                    is Result.Error -> {
                        showLoading(false)
                        showAlert(getString(R.string.registration_error))
                    }

                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility =
            View.VISIBLE else binding.progressBar.visibility = View.GONE
    }

    private fun showAlert(string: String) {
        Snackbar.make(binding.root, string, Snackbar.LENGTH_LONG).show()
    }
}