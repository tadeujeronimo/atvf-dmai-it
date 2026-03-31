package com.tadeujeronimo.deliveryapp.ui.menu

import android.content.Context
import android.content.Intent
import com.tadeujeronimo.deliveryapp.R
import com.tadeujeronimo.deliveryapp.ui.CartActivity
import com.tadeujeronimo.deliveryapp.ui.FavoriteActivity
import com.tadeujeronimo.deliveryapp.ui.HomeActivity
import com.tadeujeronimo.deliveryapp.ui.UserListActivity

object Menu {

    fun handleMenu(itemId: Int, context: Context) {
        when (itemId) {
            R.id.menu_register_customer -> {
                context.startActivity(Intent(context, UserListActivity::class.java))
            }
            R.id.menu_list_users -> {
                context.startActivity(Intent(context, UserListActivity::class.java))
            }
            R.id.menu_cart -> {
                context.startActivity(Intent(context, CartActivity::class.java))
            }
            R.id.menu_home -> {
                context.startActivity(Intent(context, HomeActivity::class.java))
            }
            /*R.id.menu_register_product -> {
                context.startActivity(Intent(context, RegisterProductActivity::class.java))
            }*/
            R.id.menu_products_favorite -> {
                context.startActivity(Intent(context, FavoriteActivity::class.java))
            }
            else -> {
                context.startActivity(Intent(context, HomeActivity::class.java))
            }
        }
    }
}