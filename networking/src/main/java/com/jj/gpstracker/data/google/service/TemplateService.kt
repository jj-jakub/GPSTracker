package com.jj.gpstracker.data.google.service

import retrofit2.Response
import retrofit2.http.GET

interface TemplateService {
    @GET("/")
    suspend fun getGoogleData(): Response<Unit>
}