package com.jj.gpstracker.di.koin

import com.jj.gpstracker.BuildConfig
import com.jj.gpstracker.data.ad.DefaultAdManager
import com.jj.gpstracker.data.ad.GetInterstitialAdUnitId
import com.jj.gpstracker.data.ad.GetMainAdUnitId
import com.jj.gpstracker.data.app.DefaultAppInfoRepository
import com.jj.gpstracker.data.app.GetIsInstalledFromValidSource
import com.jj.gpstracker.data.config.AppConfiguration
import com.jj.gpstracker.data.config.VersionTextProvider
import com.jj.gpstracker.data.network.RetrofitFactory
import com.jj.gpstracker.di.ActivityProvider
import com.jj.gpstracker.domain.ad.AdManager
import com.jj.gpstracker.domain.app.AppInfoRepository
import com.jj.gpstracker.presentation.MainRootViewModel
import com.jj.gpstracker.presentation.ui.main.MainScreenViewModel
import com.jj.gpstracker.presentation.ui.secondary.SecondaryScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single {
        AppConfiguration(
            baseUrl = BuildConfig.ServerBaseUrl,
        )
    }
    single { RetrofitFactory() }
    single {
        get<RetrofitFactory>().retrofit(
            baseUrl = get<AppConfiguration>().baseUrl,
        )
    }
    single { VersionTextProvider() }

    viewModel {
        MainScreenViewModel(
            versionTextProvider = get(),
            adManager = get(),
            getGoogleStatusUseCase = get(),
            getGoogleDataUseCase = get(),
            getIsInstalledFromValidSource = get(),
        )
    }
    viewModel {
        MainRootViewModel(
            getMainAdUnitId = get(),
        )
    }
    viewModel {
        SecondaryScreenViewModel(savedStateHandle = get())
    }
    single<AdManager> {
        DefaultAdManager(
            context = androidContext(),
            activityProvider = get(),
            getInterstitialAdUnitId = get(),
        )
    }
    single { ActivityProvider(application = androidApplication()) }
    single { GetMainAdUnitId() }
    single { GetInterstitialAdUnitId() }
    single<AppInfoRepository> { DefaultAppInfoRepository() }
    single { GetIsInstalledFromValidSource(appInfoRepository = get()) }
}