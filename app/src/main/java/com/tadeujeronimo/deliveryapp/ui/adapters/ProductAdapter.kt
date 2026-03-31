package com.tadeujeronimo.deliveryapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tadeujeronimo.deliveryapp.R
import com.bumptech.glide.Glide
import com.tadeujeronimo.deliveryapp.data.models.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var products: List<Product> = listOf()
    private lateinit var listenerHome: ProductHome

    // DataSET
    fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    fun setListener(listener: ProductHome) {
        this.listenerHome = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameProduct: TextView
        val imageProduct: ImageView

        init {
            nameProduct = view.findViewById(R.id.nameProduct)
            imageProduct = view.findViewById(R.id.imageProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameProduct.text = products[position].name

        holder.imageProduct.setOnClickListener {
            listenerHome.openProduct(products[position])
        }

        Glide
            .with(context)
            .load(products[position].image)
            .into(holder.imageProduct)
    }

    override fun getItemCount(): Int = products.size

    interface ProductHome {
        fun openProduct(product: Product)
    }
}

