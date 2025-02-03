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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class ClassAdapter: ListAdapter<Class, ClassAdapter.EventViewHolderRep>(ClassDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolderRep {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.class_item, parent, false)
        return EventViewHolderRep(view)
    }

    override fun onBindViewHolder(holder: EventViewHolderRep, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class EventViewHolderRep(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.class_name_view)
        private val startTextView = itemView.findViewById<TextView>(R.id.class_start_view)
        private val endTextView = itemView.findViewById<TextView>(R.id.class_end_view)
        private val roomTextView = itemView.findViewById<TextView>(R.id.class_room_view)
        private val dividerView = itemView.findViewById<View>(R.id.class_dividerView)


        fun bind(c: Class) {
            nameTextView.text = c.name
            startTextView.text = convertTimestampToReadable(c.start)
            endTextView.text = convertTimestampToReadable(c.end)
            roomTextView.text = c.room
            dividerView.setBackgroundColor(getRandomColor())
        }

        private fun  convertTimestampToReadable(date: Date): String {
            val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
            return formatter.format(date)
        }

        private fun getRandomColor(): Int {
            val random: Random = Random()
            return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
        }
    }





}

class ClassDiffCallback : DiffUtil.ItemCallback<Class>() {
    override fun areItemsTheSame(oldItem: Class, newItem: Class): Boolean {
        return oldItem.id == newItem.id // Comparaison des IDs
    }

    override fun areContentsTheSame(oldItem: Class, newItem: Class): Boolean {
        return oldItem == newItem // Comparaison des contenus
    }
}



