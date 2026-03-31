package com.tadeujeronimo.deliveryapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.databinding.CartItemLayoutBinding
import com.tadeujeronimo.deliveryapp.ui.interfaces.ProductCart

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val items: MutableList<Product> = mutableListOf()
    private lateinit var listener: ProductCart

    fun setItems(products: List<Product>) {
        this.items.clear()
        this.items.addAll(products)
        notifyDataSetChanged()
    }

    fun setListener(listener: ProductCart) {
        this.listener = listener
    }

    class ViewHolder constructor(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            inline fun create(
                parent: ViewGroup,
                crossinline block: (inflater: LayoutInflater, container: ViewGroup, attach: Boolean) -> ViewBinding
            ) = ViewHolder(block(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent, CartItemLayoutBinding::inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.binding as CartItemLayoutBinding).apply {
            listenerCart = listener
            product = items[position]

            Glide
                .with(holder.itemView.context)
                .load(items[position].image)
                .into(imageProduct)
        }

    }

    override fun getItemCount(): Int = items.size
}