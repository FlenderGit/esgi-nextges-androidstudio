package fr.flender.nextges.repositories.source.firestore

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import fr.flender.nextges.models.Class
import fr.flender.nextges.models.Event
import fr.flender.nextges.repositories.ClassDataSource
import fr.flender.nextges.repositories.EventDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Calendar
import java.util.Date

class FirestoreEventSource(): EventDataSource {
    override fun getAllEvents(): Flow<List<Event>> = flow {
        val firestore = FirebaseFirestore.getInstance()
        val snapshot = firestore.collection("events").get().await()
        val events: List<Event> = snapshot.documents.map { it.toObject(Event::class.java)!! }
        Log.d("Fetched events number", events.size.toString())
        emit(events)
    }

}