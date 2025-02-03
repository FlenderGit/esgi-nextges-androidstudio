package fr.flender.nextges.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.flender.nextges.repositories.ClassRepository
import fr.flender.nextges.repositories.EventRepository
import fr.flender.nextges.ui.planning.PlanningViewModel

class ActivityViewModelFactory(
    private val eventRepository: EventRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            return ActivityViewModel(eventRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
