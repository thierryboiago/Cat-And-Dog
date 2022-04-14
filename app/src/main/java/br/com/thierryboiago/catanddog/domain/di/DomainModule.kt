package br.com.thierryboiago.catanddog.domain.di

import br.com.thierryboiago.catanddog.domain.LoadCatUseCase
import br.com.thierryboiago.catanddog.domain.LoadDogUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModuleDog() + useCaseModuleCat())
    }

    private fun useCaseModuleDog(): Module {
        return module {
            factory { LoadCatUseCase(get()) }
        }
    }

    private fun useCaseModuleCat(): Module {
        return module {
            factory { LoadDogUseCase(get()) }
        }
    }

}