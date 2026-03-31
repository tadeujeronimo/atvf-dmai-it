package com.tadeujeronimo.deliveryapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tadeujeronimo.deliveryapp.R
import com.tadeujeronimo.deliveryapp.data.local.entity.OrderEntity
import java.time.format.DateTimeFormatter

class OrderAdapter :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var orders: List<OrderEntity> = listOf()

    fun setOrders(orders: List<OrderEntity>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val totalValue: TextView
        val dateOrder: TextView

        init {
            totalValue = view.findViewById(R.id.totalValueOrder)
            dateOrder = view.findViewById(R.id.dateOrder)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context

        val view =
            LayoutInflater.from(context).inflate(R.layout.order_item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.totalValue.text = orders[position].totalValueOrder
        viewHolder.dateOrder.text = orders[position].date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    override fun getItemCount() = this.orders.size

}