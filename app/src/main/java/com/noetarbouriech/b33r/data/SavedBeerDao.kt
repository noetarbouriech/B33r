package com.noetarbouriech.b33r.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedBeerDao {
    @Query("SELECT * FROM saved_beers WHERE planning = 0 ORDER BY score DESC")
    fun getAllTriedBeers(): Flow<List<SavedBeer?>>

    @Query("SELECT * FROM saved_beers WHERE planning = 1 ORDER BY score DESC")
    fun getAllPlanningBeers(): Flow<List<SavedBeer?>>

    @Query("SELECT * FROM saved_beers WHERE id = :id")
    fun getBeer(id: String): Flow<SavedBeer?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedBeer: SavedBeer)

    @Update
    suspend fun update(savedBeer: SavedBeer)

    @Delete
    suspend fun delete(savedBeer: SavedBeer)
}
