package com.jj.templateproject.domain.google.exception

import com.jj.templateproject.domain.BaseError

data class NetworkError(val code: Int, val message: String) : BaseError
