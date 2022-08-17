package com.example.retrofitwithcoroutine_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitwithcoroutine_practice.api.RetrofitInstance
import com.example.retrofitwithcoroutine_practice.databinding.ActivityMainBinding
import okio.IOException
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true

            val response = try {

                RetrofitInstance.api.getTodo()
            } catch (e: IOException) {

                Log.d("PROBLEM", "IOException You Might not have an internet conncetion: ")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.d("PROBLEM", "HTTPException")
                binding.progressBar.isVisible = false
                return@launchWhenCreated

            }

            if (response.isSuccessful && response.body() != null) {
                todoAdapter.todos = response.body()!!
            } else {
                Log.d("PROBLEM", "Response not successful")
            }
            binding.progressBar.isVisible = false
        }


    }


    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }

}