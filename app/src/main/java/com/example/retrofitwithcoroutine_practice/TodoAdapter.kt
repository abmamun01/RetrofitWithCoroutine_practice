package com.example.retrofitwithcoroutine_practice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitwithcoroutine_practice.databinding.TodoLayoutBinding
import com.example.retrofitwithcoroutine_practice.model.Data_
import com.example.retrofitwithcoroutine_practice.model.Data_Item

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    class TodoViewHolder(val binding: TodoLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        return TodoViewHolder(
            TodoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]

            tvTitle.text = todo.title
            cbDone.isChecked = todo.completed
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }


    // diffUtils
    private val diffCallBack = object : DiffUtil.ItemCallback<Data_Item>() {
        override fun areItemsTheSame(oldItem: Data_Item, newItem: Data_Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data_Item, newItem: Data_Item): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var todos: List<Data_Item>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
}