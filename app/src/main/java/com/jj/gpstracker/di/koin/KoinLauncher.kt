package com.jj.gpstracker.di.koin

import android.content.Context
import com.jj.gpstracker.core.di.coreModule
import com.jj.gpstracker.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinLauncher {

    fun startKoin(applicationContext: Context) {
        startKoin {
            androidContext(applicationContext)
            modules(mainModule, networkingModule, coreModule)
        }
    }
}
