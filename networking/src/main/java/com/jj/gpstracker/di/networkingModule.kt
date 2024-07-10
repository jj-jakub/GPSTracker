package com.jj.gpstracker.di

import com.jj.gpstracker.data.google.DefaultTemplateRepository
import com.jj.gpstracker.data.google.network.TemplateNetwork
import com.jj.gpstracker.data.google.service.TemplateService
import com.jj.gpstracker.domain.google.TemplateRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val networkingModule = module {
    single { get<Retrofit>().create(TemplateService::class.java) }

    single { TemplateNetwork(templateService = get()) }
    single<TemplateRepository> { DefaultTemplateRepository(templateNetwork = get()) }
}