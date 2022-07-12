package ru.pavelkhromov.githubapp.ui.usersdetails

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.utils.SingleEventLiveData

interface UsersDetailsContract {

    interface ViewModel {
        val userLiveData: Observable<UserEntity>
        val errorLiveData: Observable<Throwable>

        fun loadUser(user: UserEntity)
    }
}