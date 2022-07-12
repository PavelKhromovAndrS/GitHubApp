package ru.pavelkhromov.githubapp.ui.users

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface UsersContract {
    interface ViewModel {

        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>

        fun onRefresh()
    }
}