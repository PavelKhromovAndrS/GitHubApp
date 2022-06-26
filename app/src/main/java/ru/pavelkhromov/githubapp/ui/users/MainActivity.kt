package ru.pavelkhromov.githubapp.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.pavelkhromov.githubapp.app
import ru.pavelkhromov.githubapp.databinding.ActivityMainBinding
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo
import ru.pavelkhromov.githubapp.ui.usersdetails.UsersDetailsActivity

class MainActivity : AppCompatActivity(), UsersContract.View,OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter: UsersAdapter = UsersAdapter(this)
    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        presenter = extractPresenter()

        presenter.attach(this)

    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    private fun extractPresenter(): UsersContract.Presenter {
        return lastCustomNonConfigurationInstance as? UsersContract.Presenter
            ?: UsersPresenter(app.usersRepo)
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

    override fun onItemClick(user: UserEntity) {

        val intent = Intent(this@MainActivity, UsersDetailsActivity::class.java)
        intent.putExtra("key",user)
        startActivity(intent)
        showProgress(false)
    }
}