package ru.pavelkhromov.githubapp.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.pavelkhromov.githubapp.app
import ru.pavelkhromov.githubapp.databinding.ActivityMainBinding
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class MainActivity : AppCompatActivity(), UsersContract.View {

    private lateinit var binding: ActivityMainBinding
    private val adapter: UsersAdapter = UsersAdapter()
    private lateinit var presenter: UsersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()

        presenter = UsersPresenter(app.usersRepo)
        presenter.attach(this)

    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initViews() {
        showProgress(false)

        binding.refreshButton.setOnClickListener {
            presenter.onRefresh()
        }

        initRecyclerView()
    }


    override fun showUsers(data: List<UserEntity>) {
        adapter.setData(data)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter

    }
}