package fr.flender.nextges.models

import java.util.Date

data class Event (
    var id: Int = -1,
    val name: String = "",
    val sub: String = "",
    val tag: String = "",
    val start: Date = Date()
)