package ru.pavelkhromov.githubapp.domain

import ru.pavelkhromov.githubapp.domain.UserEntity

interface UsersRepo {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

}