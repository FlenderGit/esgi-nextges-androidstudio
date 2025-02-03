package fr.flender.nextges.repositories

import fr.flender.nextges.models.Class
import fr.flender.nextges.models.Event
import kotlinx.coroutines.flow.Flow
import java.util.Date

class EventRepository(private val eventSrc: EventDataSource) {

    fun getAllEvents(): Flow<List<Event>> {
        return eventSrc.getAllEvents()
    }

}