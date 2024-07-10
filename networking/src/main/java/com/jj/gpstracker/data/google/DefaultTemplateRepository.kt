package com.jj.gpstracker.data.google

import com.jj.gpstracker.data.google.network.TemplateNetwork
import com.jj.gpstracker.domain.google.TemplateRepository

class DefaultTemplateRepository(
    private val templateNetwork: TemplateNetwork,
) : TemplateRepository {

    override suspend fun getGoogleData() = templateNetwork.getGoogleData()

    override suspend fun getGoogleStatus() = templateNetwork.getGoogleStatus()
}