package com.tadeujeronimo.deliveryapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.databinding.ActivityRegisterProductBinding
import com.tadeujeronimo.deliveryapp.ui.viewmodels.RegisterProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterProductBinding
    private val registerProductViewModel: RegisterProductViewModel by viewModels()
    private var productUpdate: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productUpdate = intent.getParcelableExtra("productUpdate")

        productUpdate?.let {
            binding.edtNameProduct.setText(it.name)
            binding.edtImage.setText(it.image)
            binding.edtDescriptionProduct.setText(it.description)
            binding.edtUnitPriceProduct.setText(it.priceUnit.toString())
            binding.edtCode.setText(it.code.toString())

            binding.btnUpdateProduct.visibility = View.VISIBLE
            binding.btnRegisterProduct.visibility = View.GONE
        }

        binding.activity = this
    }

    fun registerProduct() {
        val isValid = registerProductViewModel.isValidProduct(binding)

        if (isValid) {
            val product = Product(
                null,
                binding.edtNameProduct.text.toString(),
                binding.edtDescriptionProduct.text.toString(),
                binding.edtImage.text.toString(),
                binding.edtUnitPriceProduct.text.toString().toDouble(),
                binding.edtCode.text.toString().toInt()
            )

            registerProductViewModel.createProduct(product).observe(this) {
                Log.i("teste", "teste ${it.data.toString()}")
            }
        } else {
            binding.error.visibility = View.VISIBLE
        }
    }

    fun updateProduct() {
        val isValid = registerProductViewModel.isValidProduct(binding)

        if (isValid) {
            val product = Product(
                productUpdate?.productId,
                binding.edtNameProduct.text.toString(),
                binding.edtDescriptionProduct.text.toString(),
                binding.edtImage.text.toString(),
                binding.edtUnitPriceProduct.text.toString().toDouble(),
                binding.edtCode.text.toString().toInt()
            )

            registerProductViewModel.updateProduct(product).observe(this) {
                Log.i("teste", "update ${it.data.toString()}")
            }
        }  else {
            binding.error.visibility = View.VISIBLE
        }
    }
}