package di

import network.HttpClientFactory
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import trekList.network.TreksPointApi
import trekList.presenter.TrekListViewModel

//Use factory if you want a new VM per screen.
//Use single if you want the list cached across navigation.
val sharedModule: Module = module {
    single { HttpClientFactory.create() }
    single { TreksPointApi(get()) }
    factory { TrekListViewModel(get()) }
}

fun initKoin(
    extraModules: List<Module> = emptyList()
): KoinApplication {
    return startKoin {
        modules(sharedModule + extraModules)
    }
}