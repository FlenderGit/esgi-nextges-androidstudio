package fr.flender.nextges.repositories

import fr.flender.nextges.models.Class
import kotlinx.coroutines.flow.Flow
import java.util.Date

class ClassRepository(private val classSrc: ClassDataSource) {

    fun getAllClasses(): Flow<List<Class>> {
        return classSrc.getAllClasses()
    }

    fun getClassesForDay(date: Date): Flow<List<Class>> {
        return classSrc.getClassForDay(date)
    }

}