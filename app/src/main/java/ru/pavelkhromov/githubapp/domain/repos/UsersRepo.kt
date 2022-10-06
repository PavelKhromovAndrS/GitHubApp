package ru.pavelkhromov.githubapp.domain.repos

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface UsersRepo {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun getUsers(): Single<List<UserEntity>>

    fun saveUsers(users: List<UserEntity>): Completable

}