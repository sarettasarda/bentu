package io.saretta.bentu

import android.app.Application

class TrekkingItaliaAndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // init Koin dependency injection
        di.initKoin(/*more androidModules */)
    }
}
