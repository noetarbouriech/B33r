package com.noetarbouriech.b33r.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_beers")
data class SavedBeer(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val planning: Boolean,
    val score: Int? = null
)
