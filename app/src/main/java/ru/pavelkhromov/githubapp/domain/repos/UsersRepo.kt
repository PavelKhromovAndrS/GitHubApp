package ru.pavelkhromov.githubapp.domain.repos

import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface UsersRepo {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

}