package fr.flender.nextges.ui.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.flender.nextges.models.Class
import fr.flender.nextges.models.Event
import fr.flender.nextges.repositories.ClassRepository
import fr.flender.nextges.repositories.EventRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Date

class ActivityViewModel(private val eventRepository: EventRepository): ViewModel() {

    private val _events = MutableLiveData<List<Event>>(emptyList())
    val events: LiveData<List<Event>> get() = _events


    fun fetchData() {
        viewModelScope.launch {
            eventRepository.getAllEvents()
                .catch { e -> Log.e("EventViewModel", "Erreur lors de la récupération des events", e) }
                .collect { fetchedEvents ->
                    _events.value = fetchedEvents
                }
        }
    }

}