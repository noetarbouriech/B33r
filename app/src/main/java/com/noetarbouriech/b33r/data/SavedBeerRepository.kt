package com.noetarbouriech.b33r.data

import kotlinx.coroutines.flow.Flow

interface SavedBeerRepository {
    /**
     * Retrieve all the beers from the the given data source.
     */
    fun getAllBeersStream(): Flow<List<SavedBeer>>

    /**
     * Retrieve an beer from the given data source that matches with the [id].
     */
    fun getBeerStream(id: String): Flow<SavedBeer?>

    /**
     * Insert beer in the data source
     */
    suspend fun insertBeer(beer: SavedBeer)

    /**
     * Delete beer from the data source
     */
    suspend fun deleteBeer(beer: SavedBeer)

    /**
     * Update beer in the data source
     */
    suspend fun updateBeer(beer: SavedBeer)

}