package com.tadeujeronimo.deliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tadeujeronimo.deliveryapp.R
import com.bumptech.glide.Glide
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.data.util.SharedPreferences
import com.tadeujeronimo.deliveryapp.databinding.ActivityProductBinding
import com.tadeujeronimo.deliveryapp.ui.viewmodels.RegisterProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var product: Product? = null
    private val productViewModel: RegisterProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        binding.viewModel = productViewModel

        setContentView(binding.root)
        handleClicks()
        handleObserver()

        SharedPreferences.isAdmin?.let { admin ->
            if (!admin) {
                binding.btnDeleteProduct.visibility = View.GONE
                binding.icEditProduct.visibility = View.GONE
            }
        }

        product = intent.getParcelableExtra("product")

        product?.let {
            if (it.quantity <= 0 ) {
                it.quantity = 1
            }

            productViewModel.setProduct(it)

            Glide
                .with(this)
                .load(it.image)
                .into(binding.imageOfProduct)
        }
    }

    private fun handleObserver() {
        productViewModel.product.observe(this) {
            binding.product = it
        }
    }

    private fun handleClicks() {
        binding.icEditProduct.setOnClickListener {
            val intent = Intent(this, RegisterProductActivity::class.java)
            intent.putExtra("productUpdate", product)
            startActivity(intent)
        }

        binding.btnDeleteProduct.setOnClickListener {
            it.visibility = View.GONE
            Toasty.error(this, R.string.product_delete, Toast.LENGTH_LONG, true).show();

            productViewModel.deleteProduct(product?.productId!!).observe(this) {
                Toasty.success(
                    this,
                    R.string.product_delete,
                    Toast.LENGTH_LONG,
                    true
                ).show();

                finish()
            }
        }

        binding.btnAddToCart.setOnClickListener {
            it.visibility = View.GONE

            Toasty.success(this, R.string.product_add, Toast.LENGTH_LONG, true).show();

            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("product", product)

            startActivity(intent)
        }
    }
}