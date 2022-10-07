package ru.pavelkhromov.githubapp.ui.users

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import ru.pavelkhromov.dil.inject
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class UsersViewModel : UsersContract.ViewModel {

    private val usersRepo: UsersRepo by inject<UsersRepo>()

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().onNext(true)
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(

                onSuccess = {
                    progressLiveData.mutable().onNext(false)
                    usersLiveData.mutable().onNext(it)

                },
                onError = {
                    progressLiveData.mutable().onNext(false)
                    errorLiveData.mutable().onNext(it)
                }
            )

    }

    private fun <T> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }
}