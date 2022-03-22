package com.odhiambodevelopers.prefillingroomdbdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odhiambodevelopers.prefillingroomdbdemo.data.local.NoteEntity
import com.odhiambodevelopers.prefillingroomdbdemo.databinding.NotesRowBinding


class NotesAdapter : ListAdapter<NoteEntity, NotesAdapter.MyHolder>(DiffutilCallBack) {

    object DiffutilCallBack: DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }
    inner class MyHolder(private val binding: NotesRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: NoteEntity?) {
            binding.tvTitle.text = post?.noteTitle
            binding.tvDescription.text = post?.noteDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(NotesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}