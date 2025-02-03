package fr.flender.nextges.ui.planning

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.flender.nextges.models.Class
import fr.flender.nextges.repositories.ClassRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Date

class PlanningViewModel(private val classRepository: ClassRepository ): ViewModel() {

    private val _classes = MutableLiveData<List<Class>>(emptyList())
    val classes: LiveData<List<Class>> get() = _classes


    fun fetchData(date: Date) {
        viewModelScope.launch {
            classRepository.getClassesForDay(date)
                .catch { e -> Log.e("PlanningViewModel", "Erreur lors de la récupération des classes", e) }
                .collect { fetchedEvents ->
                    _classes.value = fetchedEvents
                }
        }
    }

}