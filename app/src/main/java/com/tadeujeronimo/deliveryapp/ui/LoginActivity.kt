package com.tadeujeronimo.deliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tadeujeronimo.deliveryapp.data.Resource
import com.tadeujeronimo.deliveryapp.data.models.UserLogin
import com.tadeujeronimo.deliveryapp.data.util.SharedPreferences
import com.tadeujeronimo.deliveryapp.databinding.ActivityLoginBinding
import com.tadeujeronimo.deliveryapp.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        onClickListeners()
        setContentView(binding.root)
    }

    private fun onClickListeners() {
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterCustomerActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            loginViewModel.makeLogin(UserLogin(username, password)).observe(this) { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.btnLogin.visibility = View.GONE
                        binding.btnRegister.visibility = View.GONE
                    }
                    Resource.Status.ERROR -> {
                        binding.loading.visibility = View.GONE
                        binding.error.visibility = View.VISIBLE
                        binding.btnLogin.visibility = View.VISIBLE
                        binding.btnRegister.visibility = View.VISIBLE
                    }
                    Resource.Status.SUCCESS -> {
                        binding.loading.visibility = View.GONE
                        binding.btnLogin.visibility = View.VISIBLE

                        Toasty.success(
                            this,
                            "Login efetuado com sucesso!",
                            Toast.LENGTH_LONG,
                            true
                        ).show();

                        response.data?.let { user ->
                            SharedPreferences.initSharedPreferences(this)
                            SharedPreferences.isAdmin = user.admin
                            SharedPreferences.token = user.token
                            SharedPreferences.userEmail = user.email
                        }

                        goToHome()
                    }
               }
            }
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}