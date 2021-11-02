package com.blogspot.svdevs.selfnotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.svdevs.selfnotes.R
import com.blogspot.svdevs.selfnotes.entity.Note

class NotesAdapter(private val context: Context, private val listener: adpaterInterface)
    : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    val allNotes = ArrayList<Note>()

    inner class NotesViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val text = itemView.findViewById<TextView>(R.id.note_text)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item,parent,false))
        viewHolder.deleteBtn.setOnClickListener{
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.text.text = currentNote.note
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    interface adpaterInterface{
        fun onItemClick(note: Note)
    }
}