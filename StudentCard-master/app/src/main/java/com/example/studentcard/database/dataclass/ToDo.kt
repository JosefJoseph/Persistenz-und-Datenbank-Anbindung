package com.example.studentcard.database.dataclass

/**
 * The Data Class ToDo.
 */
data class ToDo(
    val id: Int?,
    val name: String,
    val priority: String,
    val deadline: String,
    val description: String?,
    var status: Int
)
