package br.com.thierryboiago.catanddog.data.repository.dog

import br.com.thierryboiago.catanddog.data.model.Animal
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun loadAnimal(): Flow<List<Animal>>
}