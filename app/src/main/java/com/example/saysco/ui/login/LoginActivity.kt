package com.example.saysco.ui.login

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
import com.example.saysco.data.model.User
import com.example.saysco.databinding.ActivityLoginBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.main.MainActivity
import com.example.saysco.ui.register.RegisterActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

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

    private fun login(email: String, password: String) {
        viewModel.login(email, password).observe(this) {
            if (it != null) {
                when (it) {
                    Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Error -> {
                        showLoading(false)
                        showAlert(getString(R.string.login_error))
                    }

                    is Result.Success -> {
                        showLoading(false)
//                        showAlert(getString(R.string.login_success))
                        handleLoginSuccess(it.data.token)
                    }

                }
            }
        }
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
    }
    private fun handleLoginSuccess(token: String) {
        viewModel.getDataUser(token).observe(this) { result ->
            when (result) {
                Result.Loading -> {
                    showLoading(true)
                }

                is Result.Error -> {
                    showLoading(false)
                    Log.d("get data user", "handleLoginSuccess: gagal data user ambil"+result.toString())
                    showAlert(getString(R.string.login_error)+"failed to get data user")
                }

                is Result.Success -> {
                    showLoading(false)
                    val name = result.data.data.name
                    val id = result.data.data.id
                    val email = result.data.data.email
                    val user = User(name, id, email, token)
                    saveLogin(user, token)
                }
            }
        }
    }
    private fun saveLogin(user: User, token: String) {
        viewModel.saveSession(user)
        with(binding) {
            edEmail.text?.clear()
            edPw.text?.clear()
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun goToSignUp() {
        binding.goToSignupBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
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