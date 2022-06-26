package ru.pavelkhromov.githubapp.ui.users

import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface UsersContract {
    interface View {
        fun showUsers(data: List<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }
    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }
}