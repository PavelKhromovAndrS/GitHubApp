package ru.pavelkhromov.githubapp.ui.users

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import ru.pavelkhromov.dil.inject
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class UsersViewModel : UsersContract.ViewModel {
    private val usersRepo: UsersRepo by inject()
    private val roomUsersRepoImpl: UsersRepo by inject()

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }
private fun saveDataInMemory(users:List<UserEntity>){
    roomUsersRepoImpl.saveUsers(users)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}

    private fun loadDataInMemory(){
        roomUsersRepoImpl.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                usersLiveData.mutable().onNext(it)
            }
    }
    private fun loadData() {
        progressLiveData.mutable().onNext(true)
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(

            onSuccess = {
                progressLiveData.mutable().onNext(false)
                usersLiveData.mutable().onNext(it)
                saveDataInMemory(it)

            },
            onError = {
                progressLiveData.mutable().onNext(false)
                errorLiveData.mutable().onNext(it)
                loadDataInMemory()
            }
        )

    }

    private fun <T> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }
}