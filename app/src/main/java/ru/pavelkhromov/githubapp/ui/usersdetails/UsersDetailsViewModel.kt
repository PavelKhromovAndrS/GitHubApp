package ru.pavelkhromov.githubapp.ui.usersdetails

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import ru.pavelkhromov.dil.inject
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class UsersDetailsViewModel : UsersDetailsContract.ViewModel {

    private val usersRepo: UsersRepo by inject<UsersRepo>()


    override val userLiveData: Observable<UserEntity> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()


    override fun loadUser(user: UserEntity) {
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if (it.contains(user)) {
                        userLiveData.mutable().onNext(user)
                    } else {
                        throw java.lang.IllegalStateException("No data")
                    }
                },
                onError = {
                    errorLiveData.mutable().onNext(it)
                }
            )
    }

    private fun <T> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }
}