package fr.flender.nextges.repositories.source.firestore

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import fr.flender.nextges.models.Class
import fr.flender.nextges.repositories.ClassDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Calendar
import java.util.Date

class FirestoreClassSource(): ClassDataSource {
    override fun getAllClasses(): Flow<List<Class>> = flow {
        val firestore = FirebaseFirestore.getInstance()
        val snapshot = firestore.collection("classes").get().await()
        val classes: List<Class> = snapshot.documents.map { it.toObject(Class::class.java)!! }
        Log.d("Fetched classes number", classes.size.toString())
        emit(classes)
    }

    override fun getClassForDay(date: Date): Flow<List<Class>> = flow {
        val firestore = FirebaseFirestore.getInstance()
        val startOfDay = date.toStartOfDay()
        val endOfDay = date.toEndOfDay()

        Log.d("Search classes", "Search classes between" + startOfDay.toString() + " and " + endOfDay.toString())

        val snapshot = firestore.collection("classes")
            .whereGreaterThanOrEqualTo("start", Timestamp(startOfDay) )
            .whereLessThan("start", Timestamp(endOfDay) )
            .get()
            .await()

        val classes: List<Class> = snapshot.documents.map { it.toObject(Class::class.java)!! }
        Log.d("Fetched classes for day", classes.size.toString())
        emit(classes)
    }

    private fun Date.toStartOfDay(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = this
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    private fun Date.toEndOfDay(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = this
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }
}