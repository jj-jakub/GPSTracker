package com.jj.templateproject.data.google.network

import com.jj.templateproject.data.google.service.TemplateService
import com.jj.templateproject.data.utils.toResult
import com.jj.templateproject.domain.BaseResult
import com.jj.templateproject.domain.google.exception.NetworkError

class TemplateNetwork(
    private val templateService: TemplateService,
) {

    suspend fun getGoogleData(): BaseResult<String, NetworkError> {
        val result = templateService.getGoogleData()
        return if (result.isSuccessful) {
            BaseResult.Success(result.code().toString())
        } else {
            BaseResult.Error(NetworkError(result.code(), result.message()))
        }
    }

    suspend fun getGoogleStatus(): BaseResult<Unit, NetworkError> =
        templateService.getGoogleData().toResult()
}