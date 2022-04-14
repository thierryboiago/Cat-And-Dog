package br.com.thierryboiago.catanddog.domain

import br.com.thierryboiago.catanddog.core.UseCase
import br.com.thierryboiago.catanddog.data.model.Animal
import br.com.thierryboiago.catanddog.data.repository.cat.CatRepository
import kotlinx.coroutines.flow.Flow

class LoadCatUseCase (
    private val repository: CatRepository
) : UseCase<String, List<Animal>>() {

    

    override suspend fun execute(param: String): Flow<List<Animal>> {
        return repository.loadAnimal()
    }
}