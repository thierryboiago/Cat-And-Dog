package br.com.thierryboiago.catanddog.domain

import br.com.thierryboiago.catanddog.core.UseCase
import br.com.thierryboiago.catanddog.data.model.Animal
import br.com.thierryboiago.catanddog.data.repository.dog.DogRepository
import kotlinx.coroutines.flow.Flow

class LoadDogUseCase (
    private val repository: DogRepository
) : UseCase<String, List<Animal>>() {

    

    override suspend fun execute(param: String): Flow<List<Animal>> {
        return repository.loadAnimal()
    }
}