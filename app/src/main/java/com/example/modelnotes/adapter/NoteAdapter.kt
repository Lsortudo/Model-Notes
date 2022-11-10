package com.example.modelnotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.modelnotes.R
import com.example.modelnotes.model.Note
import kotlin.random.Random

class NoteAdapter(private val context: Context, val listener: NoteClickListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val NoteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NoteList[position]

        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(
            holder.itemView.resources.getColor(
                randomColor(),
                null
            )
        )

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(NoteList[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(NoteList[holder.adapterPosition], holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return NoteList.size
    }

    fun updateList(newList: List<Note>) {
        fullList.clear()
        fullList.addAll(newList)

        NoteList.clear()
        NoteList.addAll(fullList)

        notifyDataSetChanged()
    }

    fun filterList(search: String) {
        NoteList.clear()

        for (item in fullList) {
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true) {
                NoteList.add(item)
            }
        }

        notifyDataSetChanged()
    }

    fun randomColor(): Int {
        val list = ArrayList<Int>()
        list.add(R.color.noteColor1)
        list.add(R.color.noteColor2)
        list.add(R.color.noteColor3)
        list.add(R.color.noteColor4)
        list.add(R.color.noteColor5)
        list.add(R.color.noteColor6)
        list.add(R.color.noteColor7)
        list.add(R.color.noteColor8)
        list.add(R.color.noteColor9)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notes_layout = itemView.findViewById<CardView>(R.id.cardLayout)
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val date = itemView.findViewById<TextView>(R.id.tvDate)
    }

    interface NoteClickListener {
        fun onItemClicked(note: Note) {

        }

        fun onLongItemClicked(note: Note, cardView: CardView) {

        }
    }
}