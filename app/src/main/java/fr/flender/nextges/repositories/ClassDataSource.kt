package fr.flender.nextges.repositories

import fr.flender.nextges.models.Class
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ClassDataSource {

    fun getAllClasses(): Flow<List<Class>>
    fun getClassForDay(date: Date): Flow<List<Class>>


}