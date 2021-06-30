package com.picpay.desafio.android.user.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.user.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.user.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        observeViewModel()
        userViewModel.getUsers()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE
    }

    private fun observeViewModel() {
        userViewModel.loading.observe(this, Observer { isLoading ->
            if(isLoading){
                progressBar.visibility = View.VISIBLE
            }
            else {
                progressBar.visibility = View.GONE
            }
        })

        userViewModel.users.observe(this, Observer { usersList ->
            progressBar.visibility = View.GONE
            adapter.users = usersList
        })

        userViewModel.error.observe(this, Observer { errorMessage ->
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            Toast.makeText(this@MainActivity, getString(errorMessage), Toast.LENGTH_SHORT).show()
        })
    }
}
