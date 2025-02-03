package fr.flender.nextges.ui.planning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.flender.nextges.repositories.ClassRepository

class PlanningViewModelFactory(
    private val classRepository: ClassRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanningViewModel::class.java)) {
            return PlanningViewModel(classRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}