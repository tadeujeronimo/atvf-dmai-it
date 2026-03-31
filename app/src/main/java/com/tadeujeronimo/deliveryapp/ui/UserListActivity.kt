package com.tadeujeronimo.deliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tadeujeronimo.deliveryapp.data.Resource
import com.tadeujeronimo.deliveryapp.data.models.User
import com.tadeujeronimo.deliveryapp.databinding.ActivityUserListBinding
import com.tadeujeronimo.deliveryapp.ui.adapters.UserAdapter
import com.tadeujeronimo.deliveryapp.ui.viewmodels.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class UserListActivity : AppCompatActivity(), UserAdapter.UserActions {
    private lateinit var binding: ActivityUserListBinding
    private lateinit var adapter: UserAdapter
    private val userListViewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadUsers()
    }

    override fun onResume() {
        super.onResume()
        loadUsers()
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter()
        adapter.setListener(this)
        binding.rcListUsers.adapter = adapter
    }

    private fun loadUsers() {
        userListViewModel.getUsers().observe(this) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    // no-op
                }

                Resource.Status.ERROR -> {
                    Toasty.error(this, "Erro ao buscar usuarios", Toast.LENGTH_LONG, true).show()
                }

                Resource.Status.SUCCESS -> {
                    response.data?.let { users ->
                        adapter.setUsers(users)
                    }
                }
            }
        }
    }

    override fun onEdit(user: User) {
        val intent = Intent(this, RegisterCustomerActivity::class.java)
        intent.putExtra("userId", user.userId)
        intent.putExtra("name", user.name)
        intent.putExtra("email", user.email)
        intent.putExtra("password", user.password)
        intent.putExtra("image", user.image)
        intent.putExtra("isAdmin", user.isAdmin)

        val address = user.address.firstOrNull()
        intent.putExtra("street", address?.street ?: "")
        intent.putExtra("number", address?.number ?: 0)
        intent.putExtra("complement", address?.complement ?: "")
        intent.putExtra("cep", address?.cep ?: "")

        startActivity(intent)
    }

    override fun onDelete(user: User) {
        val userId = user.userId

        if (userId.isNullOrEmpty()) {
            Toasty.error(this, "Usuario sem id para exclusao", Toast.LENGTH_LONG, true).show()
            return
        }

        userListViewModel.deleteUser(userId).observe(this) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    // no-op
                }

                Resource.Status.ERROR -> {
                    Toasty.error(this, "Erro ao deletar usuario", Toast.LENGTH_LONG, true).show()
                }

                Resource.Status.SUCCESS -> {
                    Toasty.success(this, "Usuario deletado com sucesso!", Toast.LENGTH_LONG, true)
                        .show()
                    loadUsers()
                }
            }
        }
    }
}
