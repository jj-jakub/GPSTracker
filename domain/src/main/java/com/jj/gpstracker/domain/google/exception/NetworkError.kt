package com.jj.gpstracker.domain.google.exception

import com.jj.gpstracker.domain.BaseError

data class NetworkError(val code: Int, val message: String) : BaseError
