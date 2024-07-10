package com.jj.gpstracker.domain.google

import com.jj.gpstracker.domain.BaseResult
import com.jj.gpstracker.domain.google.exception.NetworkError

interface TemplateRepository {
    suspend fun getGoogleData(): BaseResult<String, NetworkError>
    suspend fun getGoogleStatus(): BaseResult<Unit, NetworkError>
}