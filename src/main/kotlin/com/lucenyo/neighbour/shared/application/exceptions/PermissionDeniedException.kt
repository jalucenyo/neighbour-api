package com.lucenyo.neighbour.shared.application.exceptions

import org.springframework.http.HttpStatus

class PermissionDeniedException(msg: String, code: Int = HttpStatus.UNAUTHORIZED.value()) : ApiException(msg, code)
