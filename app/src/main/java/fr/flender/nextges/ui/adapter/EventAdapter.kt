package fr.flender.nextges.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.flender.nextges.R
import fr.flender.nextges.models.Class
import fr.flender.nextges.models.Event
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class EventAdapter: ListAdapter<Event, EventAdapter.EventViewHolderRep>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolderRep {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventViewHolderRep(view)
    }

    override fun onBindViewHolder(holder: EventViewHolderRep, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class EventViewHolderRep(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.event_name_text)
        private val tagTextView = itemView.findViewById<TextView>(R.id.event_tag_text)
        private val startTextView = itemView.findViewById<TextView>(R.id.event_date_text)
        private val subTextView = itemView.findViewById<TextView>(R.id.event_sub_text)


        fun bind(c: Event) {
            nameTextView.text = c.name
            tagTextView.text = c.tag
            startTextView.text = convertTimestampToReadable(c.start)
            subTextView.text = c.sub
        }

        private fun  convertTimestampToReadable(date: Date): String {
            val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
            return formatter.format(date)
        }

    }

}

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id // Comparaison des IDs
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem // Comparaison des contenus
    }
}



