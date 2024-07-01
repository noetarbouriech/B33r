package com.noetarbouriech.b33r.data

import kotlinx.coroutines.flow.Flow

class OfflineBeersRepository(private val beerDao: SavedBeerDao) : SavedBeerRepository {
    override fun getAllTriedBeersStream(): Flow<List<SavedBeer?>> = beerDao.getAllTriedBeers()

    override fun getAllPlanningBeersStream(): Flow<List<SavedBeer?>> = beerDao.getAllPlanningBeers()

    override fun getBeerStream(id: String): Flow<SavedBeer?> = beerDao.getBeer(id)

    override suspend fun insertBeer(beer: SavedBeer) = beerDao.insert(beer)

    override suspend fun deleteBeer(beer: SavedBeer) = beerDao.delete(beer)

    override suspend fun updateBeer(beer: SavedBeer) = beerDao.update(beer)
}
