package ru.pavelkhromov.githubapp.ui.usersdetails

import io.reactivex.rxjava3.core.Observable
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface UsersDetailsContract {

    interface ViewModel {
        val userLiveData: Observable<UserEntity>
        val errorLiveData: Observable<Throwable>

        fun loadUser(user: UserEntity)
    }
}