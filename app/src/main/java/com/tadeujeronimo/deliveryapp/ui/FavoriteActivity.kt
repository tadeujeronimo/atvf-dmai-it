package com.tadeujeronimo.deliveryapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.tadeujeronimo.deliveryapp.data.Resource
import com.tadeujeronimo.deliveryapp.databinding.ActivityFavoriteBinding
import com.tadeujeronimo.deliveryapp.ui.adapters.FavoriteAdapter
import com.tadeujeronimo.deliveryapp.ui.viewmodels.RegisterProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter

    private val productViewModel: RegisterProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rc = binding.rcFavoriteProduct
        adapter = FavoriteAdapter()
        rc.adapter = adapter

        setupObserver()
    }

    private fun setupObserver() {
        productViewModel.getAllProductsFavorite().observe(this) { response ->
            when(response.status) {
                Resource.Status.LOADING -> {
                    //
                }
                Resource.Status.ERROR -> {
                    Toasty.error(this, "Um erro ocorreu ao buscar os produtos favoritos", Toast.LENGTH_LONG, true).show();
                }
                Resource.Status.SUCCESS -> {
                    response.data?.let {
                        adapter.setProductsFavorites(it)
                        binding.textEmptyFavorites.visibility = if (it.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
                    }
                }
            }
        }
    }
}