package ru.pavelkhromov.githubapp.ui.usersdetails

import ru.pavelkhromov.githubapp.domain.entities.UserEntity


class UsersDetailsPresenter(
) : UsersDetailsContract.Presenter {

    private var view: UsersDetailsContract.View? = null
    private var user: UserEntity? = null

    override fun attach(view: UsersDetailsContract.View) {
        this.view = view

    }

    override fun detach() {
        view = null
    }

    override fun loadUser(user: UserEntity) {
        view?.showUser(user)
    }

}