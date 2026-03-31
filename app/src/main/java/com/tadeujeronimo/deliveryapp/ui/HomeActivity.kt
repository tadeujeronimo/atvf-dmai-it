package com.tadeujeronimo.deliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tadeujeronimo.deliveryapp.R
import com.tadeujeronimo.deliveryapp.data.Resource
import com.tadeujeronimo.deliveryapp.data.local.entity.AddressEntity
import com.tadeujeronimo.deliveryapp.data.models.Address
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.data.util.SharedPreferences
import com.tadeujeronimo.deliveryapp.databinding.ActivityHomeBinding
import com.tadeujeronimo.deliveryapp.ui.menu.Menu
import com.tadeujeronimo.deliveryapp.ui.adapters.ProductAdapter
import com.tadeujeronimo.deliveryapp.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), ProductAdapter.ProductHome {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var spinner: Spinner
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.activity = this

        setContentView(binding.root)

        observers()
        handleMenu()
        setupRecyclerView()
        handleAddress(binding)
        handleSaveAddress(binding)
        spinner = binding.spinnerAddress

        onSelectedItemSpinner()
    }


    fun onClickOrders() {
        startActivity(Intent(this, OrderActivity::class.java))
    }

    private fun observers() {
        homeViewModel.getProducts().observe(this) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    //
                }

                Resource.Status.ERROR -> {
                    Toasty.error(
                        this,
                        "Um erro ocorreu ao buscar os produtos",
                        Toast.LENGTH_LONG,
                        true
                    ).show();
                }

                Resource.Status.SUCCESS -> {
                    response.data?.let {
                        adapter.setProducts(it)
                    }
                }
            }
        }

        homeViewModel.getAllAddress().observe(this) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    //
                }

                Resource.Status.ERROR -> {
                    Toasty.error(
                        this,
                        "Um erro ocorreu ao buscar os enderecos no banco de dados",
                        Toast.LENGTH_LONG,
                        true
                    ).show();
                }

                Resource.Status.SUCCESS -> {
                    response.data?.let {
                        val items: MutableList<String> = mutableListOf()

                        it.forEach { address ->
                            if (address.selected) {
                                items.add(0, address.address)
                            } else {
                                items.add(address.address)
                            }
                        }

                        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

                        spinner.adapter = adapter
                    }
                }
            }
        }
    }

    private fun onSelectedItemSpinner() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val addressSelected = spinner.selectedItem
                homeViewModel.updateAddressSelection(addressSelected.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter()
        adapter.setListener(this)
        binding.rcListProduct.adapter = adapter
    }

    private fun handleAddress(binding: ActivityHomeBinding) {
        binding.iconAddAddress.setOnClickListener {
            binding.edAddress.visibility = View.VISIBLE
            binding.btnFinishAddress.visibility = View.VISIBLE
        }
    }

    private fun handleSaveAddress(binding: ActivityHomeBinding) {
        binding.btnFinishAddress.setOnClickListener {
            binding.edAddress.visibility = View.INVISIBLE
            binding.btnFinishAddress.visibility = View.GONE

            homeViewModel.insertAddress(binding.edAddress.text.toString())

            binding.edAddress.text.clear()
        }
    }

    private fun handleMenu() {
        val menu = binding.menu.bottomNavigation
        menu.selectedItemId = R.id.menu_home

        SharedPreferences.isAdmin?.let { admin ->
            if (!admin) {
                /*binding.menu.bottomNavigation.menu.findItem(R.id.menu_register_product).isVisible =
                    false*/

                binding.menu.bottomNavigation.menu.findItem(R.id.menu_register_customer).isVisible =
                    false

                binding.menu.bottomNavigation.menu.findItem(R.id.menu_list_users).isVisible =
                    false
            }
        }

        menu.setOnNavigationItemSelectedListener {
            Menu.handleMenu(it.itemId, this)
            true
        }
    }

    override fun openProduct(product: Product) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("product", product)

        startActivity(intent)
    }
}



