package ru.pavelkhromov.githubapp.ui.usersdetails

import androidx.lifecycle.LiveData
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.utils.SingleEventLiveData

interface UsersDetailsContract {

    interface ViewModel {
        val userLiveData: LiveData<UserEntity>
        val errorLiveData: SingleEventLiveData<Throwable>

        fun loadUser(user: UserEntity)
    }
}