package ru.pavelkhromov.githubapp.ui.users

import androidx.lifecycle.LiveData
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface UsersContract {
    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onRefresh()
    }
}