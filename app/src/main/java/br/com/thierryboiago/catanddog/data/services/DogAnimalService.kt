package br.com.thierryboiago.catanddog.data.services

import br.com.thierryboiago.catanddog.data.model.Animal
import retrofit2.http.GET

interface DogAnimalService {

    @GET("v1/images/search")
    suspend fun loadAnimal() : List<Animal>
}