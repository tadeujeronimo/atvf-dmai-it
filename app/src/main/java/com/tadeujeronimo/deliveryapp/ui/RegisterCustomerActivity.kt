package com.tadeujeronimo.deliveryapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tadeujeronimo.deliveryapp.data.Resource
import com.tadeujeronimo.deliveryapp.data.models.Address
import com.tadeujeronimo.deliveryapp.data.models.User
import com.tadeujeronimo.deliveryapp.data.util.SharedPreferences
import com.tadeujeronimo.deliveryapp.databinding.ActivityRegisterCustomerBinding
import com.tadeujeronimo.deliveryapp.ui.viewmodels.RegisterCostumerViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class RegisterCustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterCustomerBinding
    private val registerCostumerViewModel: RegisterCostumerViewModel by viewModels()
    private var isAdmin: Boolean = false
    private var userIdToUpdate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterCustomerBinding.inflate(layoutInflater)
        binding.activity = this

        if (SharedPreferences.isAdmin != null && SharedPreferences.isAdmin == true) {
            binding.radioGroup.visibility = View.VISIBLE
        }

        userIdToUpdate = intent.getStringExtra("userId")

        userIdToUpdate?.let {
            binding.btnRegister.text = "Atualizar"
            binding.edtName.setText(intent.getStringExtra("name") ?: "")
            binding.edtEmail.setText(intent.getStringExtra("email") ?: "")
            binding.edtPassword.setText(intent.getStringExtra("password") ?: "")
            binding.edtImagem.setText(intent.getStringExtra("image") ?: "")
            binding.edtStreet.setText(intent.getStringExtra("street") ?: "")
            binding.edtNumberOfStreet.setText(intent.getIntExtra("number", 0).toString())
            binding.edtComplement.setText(intent.getStringExtra("complement") ?: "")
            binding.edtCep.setText(intent.getStringExtra("cep") ?: "")

            isAdmin = intent.getBooleanExtra("isAdmin", false)

            if (binding.radioGroup.visibility == View.VISIBLE) {
                if (isAdmin) {
                    binding.radioAdmin.isChecked = true
                } else {
                    binding.radioComum.isChecked = true
                }
            }
        }

        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            it.visibility = View.GONE

            binding.let { registerUser ->
                val number = registerUser.edtNumberOfStreet.text.toString().toIntOrNull() ?: 0

                val user = User(
                    null,
                    registerUser.edtName.text.toString(),
                    registerUser.edtEmail.text.toString(),
                    registerUser.edtPassword.text.toString(),
                    registerUser.edtImagem.text.toString(),
                    isAdmin,
                    listOf(
                        Address(
                            registerUser.edtStreet.text.toString(),
                            number,
                            registerUser.edtComplement.text.toString(),
                            registerUser.edtCep.text.toString()
                        )
                    )
                )

                val operation = if (!userIdToUpdate.isNullOrEmpty()) {
                    registerCostumerViewModel.updateUser(userIdToUpdate!!, user)
                } else {
                    registerCostumerViewModel.createUser(user)
                }

                operation.observe(this) { response ->
                    when (response.status) {
                        Resource.Status.LOADING -> {
                            //
                        }
                        Resource.Status.ERROR -> {
                            it.visibility = View.VISIBLE
                            Toasty.error(this, "Erro ao registrar um usuario", Toast.LENGTH_LONG, true).show();
                        }
                        Resource.Status.SUCCESS -> {
                            Toasty.success(
                                this,
                                if (userIdToUpdate.isNullOrEmpty()) "Usuario registrado com sucesso!" else "Usuario atualizado com sucesso!",
                                Toast.LENGTH_LONG,
                                true
                            ).show();
                            response.data?.let {
                                finish()
                            }
                        }
                    }
                }
            }
        }
    }

    fun onRadioButtonClick(view: View) {
        when (view.id) {
            binding.radioAdmin.id -> {
                isAdmin = true
            }
            binding.radioComum.id -> {
                isAdmin = false
            }
        }
    }
}