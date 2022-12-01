package com.lucenyo.neighbour.shared.application.exceptions

import org.springframework.http.HttpStatus

class NotFoundException(msg: String, code: Int = HttpStatus.NOT_FOUND.value()) : ApiException(msg, code)
