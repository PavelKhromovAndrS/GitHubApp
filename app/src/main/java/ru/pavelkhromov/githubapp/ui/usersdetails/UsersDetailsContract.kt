package ru.pavelkhromov.githubapp.ui.usersdetails

import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface UsersDetailsContract {

    interface View {
        fun showUser(user: UserEntity)
        fun showError(throwable: Throwable)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadUser(user: UserEntity)
    }
}