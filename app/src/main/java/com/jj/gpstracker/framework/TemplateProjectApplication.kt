package com.jj.gpstracker.framework

import android.app.Application
import com.jj.gpstracker.core.data.back4app.InitializeBack4App
import com.jj.gpstracker.di.ActivityProvider
import com.jj.gpstracker.di.koin.KoinLauncher
import com.jj.gpstracker.domain.ad.AdManager
import org.koin.android.ext.android.inject

class TemplateProjectApplication : Application() {

    private val koinLauncher = KoinLauncher()

    private val activityProvider: ActivityProvider by inject()
    private val adManager: AdManager by inject()
    private val initializeBack4App: InitializeBack4App by inject()

    override fun onCreate() {
        super.onCreate()
        koinLauncher.startKoin(this)
        activityProvider.start()
        adManager.initAds()
        initializeBack4App()
    }
}