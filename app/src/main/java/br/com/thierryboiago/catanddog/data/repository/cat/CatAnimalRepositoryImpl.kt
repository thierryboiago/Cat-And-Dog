package br.com.thierryboiago.catanddog.data.repository.cat

import br.com.thierryboiago.catanddog.core.RemoteException
import br.com.thierryboiago.catanddog.data.services.CatAnimalService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CatAnimalRepositoryImpl(private val serviceCat: CatAnimalService) : CatRepository {
    override suspend fun loadAnimal() = flow {
        try {
            val repoList = serviceCat.loadAnimal()
            emit(repoList)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possivel fazer a busca no momento!")
        }
    }


}