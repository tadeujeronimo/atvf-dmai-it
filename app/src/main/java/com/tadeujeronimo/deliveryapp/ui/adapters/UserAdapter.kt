package com.tadeujeronimo.deliveryapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tadeujeronimo.deliveryapp.R
import com.tadeujeronimo.deliveryapp.data.models.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var users: List<User> = listOf()
    private lateinit var listenerUser: UserActions

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    fun setListener(listener: UserActions) {
        this.listenerUser = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageUser: ImageView = view.findViewById(R.id.imageUser)
        val nameUser: TextView = view.findViewById(R.id.nameUser)
        val emailUser: TextView = view.findViewById(R.id.emailUser)
        val roleUser: TextView = view.findViewById(R.id.roleUser)
        val editUser: ImageView = view.findViewById(R.id.icEditUser)
        val deleteUser: ImageView = view.findViewById(R.id.icDeleteUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        holder.nameUser.text = user.name
        holder.emailUser.text = user.email
        holder.roleUser.text = if (user.isAdmin) "ADMIN" else "COMUM"

        Glide
            .with(holder.itemView.context)
            .load(user.image)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.imageUser)

        holder.editUser.setOnClickListener {
            listenerUser.onEdit(user)
        }

        holder.deleteUser.setOnClickListener {
            listenerUser.onDelete(user)
        }
    }

    override fun getItemCount(): Int = users.size

    interface UserActions {
        fun onEdit(user: User)
        fun onDelete(user: User)
    }
}
