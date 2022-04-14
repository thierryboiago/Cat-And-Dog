package br.com.thierryboiago.catanddog.data.repository.dog

import br.com.thierryboiago.catanddog.core.RemoteException
import br.com.thierryboiago.catanddog.data.services.DogAnimalService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class DogAnimalRepositoryImpl(private val serviceDog: DogAnimalService) : DogRepository {
    override suspend fun loadAnimal() = flow {
        try {
            val repoList = serviceDog.loadAnimal()
            emit(repoList)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possivel fazer a busca no momento!")
        }
    }


}