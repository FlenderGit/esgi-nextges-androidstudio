package fr.flender.nextges.models

import com.google.firebase.Timestamp
import java.util.Date

data class Class (
    var id: String = "",
    val name: String = "",
    val room: String = "",
    val block: Int = -1,
    val start: Date = Date(),
    val end: Date = Date()
)