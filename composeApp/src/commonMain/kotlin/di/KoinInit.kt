package di

import network.HttpClientFactory
import network.TrekkingItaliaApi
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule: Module = module {
    single { HttpClientFactory.create() }
    single { TrekkingItaliaApi(get()) }
}

fun initKoin(
    extraModules: List<Module> = emptyList()
): KoinApplication {
    return startKoin {
        modules(sharedModule + extraModules)
    }
}