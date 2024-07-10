package com.jj.gpstracker.core.di

import com.jj.gpstracker.core.data.back4app.InitializeBack4App
import com.jj.gpstracker.domain.google.GetGoogleDataUseCase
import com.jj.gpstracker.domain.google.GetGoogleStatusUseCase
import com.jj.gpstracker.core.data.notifications.AndroidNotificationManager
import com.jj.gpstracker.domain.notifications.NotificationManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { GetGoogleStatusUseCase(templateRepository = get()) }
    single { GetGoogleDataUseCase(templateRepository = get()) }
    single<NotificationManager> { AndroidNotificationManager(context = androidContext()) }
    single<InitializeBack4App> { InitializeBack4App(applicationContext = androidContext()) }
}