package br.com.thierryboiago.catanddog.data.di

import android.util.Log
import br.com.thierryboiago.catanddog.data.repository.cat.CatRepository
import br.com.thierryboiago.catanddog.data.repository.cat.CatAnimalRepositoryImpl
import br.com.thierryboiago.catanddog.data.repository.dog.DogAnimalRepositoryImpl
import br.com.thierryboiago.catanddog.data.repository.dog.DogRepository
import br.com.thierryboiago.catanddog.data.services.CatAnimalService
import br.com.thierryboiago.catanddog.data.services.DogAnimalService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private const val OK_HTTP = "OkHttp"

    fun load() {
        loadKoinModules(networkModules() + animalModuleCat() + animalModuleDog())
    }

    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createServiceCat<CatAnimalService>(get(), get())
            }

            single {
                createServiceDog<DogAnimalService>(get(), get())
            }

        }
    }

    private fun animalModuleCat(): Module {
        return module {
            single<CatRepository> { CatAnimalRepositoryImpl(get()) }
        }
    }

    private fun animalModuleDog(): Module {
        return module {
            single<DogRepository> { DogAnimalRepositoryImpl(get()) }
        }
    }

    private inline fun <reified T> createServiceCat(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
    private inline fun <reified T> createServiceDog(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}