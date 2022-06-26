package ru.pavelkhromov.githubapp.ui.users

import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class UsersPresenter(
    private val usersRepo: UsersRepo
) : UsersContract.Presenter {

    private var userList: List<UserEntity>? = null
    private var inProgress: Boolean = false
    private var view: UsersContract.View? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        userList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        view?.showProgress(true)
        inProgress = true
        usersRepo.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
                userList = it
                inProgress = false
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                inProgress = false
            }
        )
    }
}