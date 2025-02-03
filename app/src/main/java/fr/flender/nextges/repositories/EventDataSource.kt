package fr.flender.nextges.repositories

import fr.flender.nextges.models.Class
import fr.flender.nextges.models.Event
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface EventDataSource {
    fun getAllEvents(): Flow<List<Event>>
}