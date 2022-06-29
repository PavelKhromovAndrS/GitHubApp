package ru.pavelkhromov.githubapp.ui.usersdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class UsersDetailsViewModel(
    private val usersRepo: UsersRepo
) : UsersDetailsContract.ViewModel {

    override val userLiveData: LiveData<UserEntity> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = MutableLiveData()

    override fun loadUser(user: UserEntity) {

        usersRepo.getUsers(
            onSuccess = {
                if (it.contains(user)) {
                    userLiveData.mutable().postValue(user)
                } else {
                    throw java.lang.IllegalStateException("No data")
                }

            },
            onError = {
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }

}