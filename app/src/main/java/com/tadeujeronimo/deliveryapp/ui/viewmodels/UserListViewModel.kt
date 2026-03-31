package com.tadeujeronimo.deliveryapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tadeujeronimo.deliveryapp.data.Resource
import com.tadeujeronimo.deliveryapp.data.models.User
import com.tadeujeronimo.deliveryapp.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getUsers(): LiveData<Resource<List<User>>> = liveData {
        emit(Resource.loading())

        try {
            val result = userRepository.getUsers()

            if (result.isSuccessful && result.body() != null) {
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception no get users ${e}")
            emit(Resource(Resource.Status.ERROR))
        }
    }

    fun deleteUser(idUser: String): LiveData<Resource<Map<String, String>>> = liveData {
        emit(Resource.loading())

        try {
            val result = userRepository.deleteUser(idUser)

            if (result.isSuccessful && result.body() != null) {
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception no delete user ${e}")
            emit(Resource(Resource.Status.ERROR))
        }
    }
}
