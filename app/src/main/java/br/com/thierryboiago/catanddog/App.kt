package br.com.thierryboiago.catanddog

import android.app.Application
import br.com.thierryboiago.catanddog.data.di.DataModule
import br.com.thierryboiago.catanddog.domain.di.DomainModule
import br.com.thierryboiago.catanddog.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()

    }
}