package com.jj.templateproject.data.utils

import com.jj.templateproject.domain.BaseResult
import com.jj.templateproject.domain.google.exception.NetworkError
import retrofit2.Response

fun <T : Any> Response<T>.toResult(): BaseResult<T, NetworkError> {
    val body = body()
    return if (isSuccessful && body != null) {
        BaseResult.Success(body)
    } else {
        BaseResult.Error(NetworkError(code(), message()))
    }
}