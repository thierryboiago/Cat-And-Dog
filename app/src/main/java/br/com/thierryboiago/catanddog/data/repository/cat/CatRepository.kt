package br.com.thierryboiago.catanddog.data.repository.cat

import br.com.thierryboiago.catanddog.data.model.Animal
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    suspend fun loadAnimal(): Flow<List<Animal>>
}