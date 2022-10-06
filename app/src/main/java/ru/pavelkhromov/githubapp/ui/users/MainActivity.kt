package ru.pavelkhromov.githubapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.disposables.CompositeDisposable

import ru.pavelkhromov.githubapp.databinding.ActivityMainBinding
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo
import ru.pavelkhromov.githubapp.ui.usersdetails.UsersDetailsActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter: UsersAdapter = UsersAdapter(this)

    @Inject
    lateinit var usersRepo: UsersRepo

    @Inject
    lateinit var roomUsersRepo: RoomUsersRepoImpl

    private val viewModel: UsersViewModel by lazy { UsersViewModel(usersRepo,roomUsersRepo) }

    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        app.appComponent.injectMainActivity(this)
        initViews()

        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe { showProgress(it) },
            viewModel.usersLiveData.subscribe { showUsers(it) },
            viewModel.errorLiveData.subscribe { showError(it) }
        )
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }



    private fun initViews() {
        showProgress(false)
        binding.refreshButton.onClickObserver.subscribe {
            if (it) {
                viewModel.onRefresh()
            }
        }
        initRecyclerView()
    }


    private fun showUsers(data: List<UserEntity>) {
        adapter.setData(data)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter

    }

    override fun onItemClick(user: UserEntity) {

        val intent = Intent(this@MainActivity, UsersDetailsActivity::class.java)
        intent.putExtra("key", user)
        startActivity(intent)
        showProgress(false)
    }
}