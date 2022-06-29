package ru.pavelkhromov.githubapp.ui.usersdetails

import androidx.lifecycle.LiveData
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface UsersDetailsContract {

    interface ViewModel {
        val userLiveData: LiveData<UserEntity>
        val errorLiveData: LiveData<Throwable>

        fun loadUser(user: UserEntity)
    }
}